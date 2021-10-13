package com.epam.jwd.service;

import com.epam.jwd.repository.Car;
import com.epam.jwd.repository.CarPark;
import com.epam.jwd.repository.CarParkLot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CarParkManager {
    private static final Logger logger = LogManager.getLogger(CarParkManager.class);

    private static final String ACQUIRED_FAILED = "acquired failed";
    private static final String CHANGED = " changed ";
    private static final String TO = " to ";
    private static final String DID_NOT_WAIT_LOT_TO_EXCHANGE = " didn't wait for the lot to for exchange";
    private static final int PARK_SIZE = 5;
    private final CarPark carPark;
    private final Queue<CarParkLot> lots = new ConcurrentLinkedDeque<>();
    private final Semaphore semaphore = new Semaphore(PARK_SIZE, true);
    private final Exchanger<CarParkLot> exchanger = new Exchanger<>();

    public CarParkManager(CarPark carPark) {
        this.carPark = carPark;
    }

    public void addToLots() {
        lots.addAll(carPark.getLots());
    }

    public Queue<CarParkLot> getLots() {
        return new LinkedList<>(lots);
    }

    public CarParkLot takeLot(long maxWAitMillis) {
        CarParkLot carParkLot = null;
        try {
            if (semaphore.tryAcquire(maxWAitMillis, TimeUnit.MILLISECONDS)) {
                carParkLot = lots.poll();
                Validator.isNull(carParkLot);
                carParkLot.setBusy(true);
            }
        } catch (InterruptedException e) {
            logger.error(ACQUIRED_FAILED);
            Thread.currentThread().interrupt();
        } catch (InappropriateValueException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return carParkLot;
    }

    public void returnLot(CarParkLot carParkLot, Car car) {
        tryChangeLot(carParkLot, car);
        carParkLot.setBusy(false);
        lots.add(carParkLot);
        semaphore.release();
    }

    public void tryChangeLot(CarParkLot carParkLot, Car car) {
        CarParkLot lot = carParkLot;

        try {
            if ((carParkLot = exchanger.exchange(carParkLot, 400, TimeUnit.MILLISECONDS)).getLotId()
                    == (lot.getLotId() + 1) || carParkLot.getLotId() == (lot.getLotId() - 1)) {

                logger.info(car + CHANGED + lot + TO + carParkLot);
                carParkLot.setBusy(true);
                carParkLot.using();
            }
        } catch (TimeoutException e) {
            logger.info(car + DID_NOT_WAIT_LOT_TO_EXCHANGE);
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
