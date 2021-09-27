package com.epam.jwd.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarParkLotTest {
    private final CarParkLot carParkLot = new CarParkLot(2);

    @Test
    void isBusyTest() {
        carParkLot.setBusy(true);
        assertTrue(carParkLot.isBusy);
    }

    @Test
    void getLotIdTest() {
        assertEquals(carParkLot.getLotId(), 2);
    }
}