package com.epam.jwd.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class CarParkLot {
    private static final Logger logger = LogManager.getLogger(CarParkLot.class);
    private static final String USING_BY = " using by ";
    private int lotId;
    public boolean isBusy;

    public CarParkLot(int id) {
        this.lotId = id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public int getLotId() {
        return lotId;
    }

    public void using() {
        try {
            logger.info(this + USING_BY + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarParkLot that = (CarParkLot) o;

        if (lotId != that.lotId) return false;
        return isBusy == that.isBusy;
    }

    @Override
    public int hashCode() {
        int result = lotId;
        result = 31 * result + (isBusy ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\"Lot #" + getLotId() + "\"";
    }
}
