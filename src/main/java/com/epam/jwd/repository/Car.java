package com.epam.jwd.repository;

import com.epam.jwd.service.CarParkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread {
    private static final Logger logger = LogManager.getLogger(Car.class);

    private static final String THREAD_NAME = "Car #";
    private static final String LEFT_WITHOUT_WAITING = " left without waiting";
    private static final String LEFT = " left";
    private static final String LOT_IS_RELEASED = " is released";
    private final CarParkManager carParkManager;
    private final ReentrantLock lock = new ReentrantLock();
    private int carId;

    public Car(CarParkManager carParkManager, int id) {
        this.carParkManager = carParkManager;
        this.carId = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public void run() {
        this.setName(THREAD_NAME + getCarId());
        CarParkLot carParkLot;

        carParkLot = carParkManager.takeLot(20000);

        if (carParkLot == null) {
            logger.info(this + LEFT_WITHOUT_WAITING);
            Thread.currentThread().interrupt();
            return;
        }

        carParkLot.using();

        lock.lock();
        carParkManager.returnLot(carParkLot, this);
        logger.info(this + LEFT);

        if (!carParkLot.isBusy) {
            logger.info(carParkLot + LOT_IS_RELEASED);
        }
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Car #" + getCarId();
    }
}
