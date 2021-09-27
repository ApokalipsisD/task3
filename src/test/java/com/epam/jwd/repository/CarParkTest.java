package com.epam.jwd.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CarParkTest {
    private static final CarPark carPark = new CarPark();

    @BeforeEach
    void init() {
        carPark.addToLots(new ArrayList<>() {
            {
                this.add(new CarParkLot(1));
                this.add(new CarParkLot(2));
                this.add(new CarParkLot(3));
                this.add(new CarParkLot(4));
                this.add(new CarParkLot(5));
            }
        });
    }

    @AfterEach
    void clearLots() {
        carPark.getLots().clear();
    }

    @Test
    void shouldGetLots() {
        List<CarParkLot> newLots = new ArrayList<>() {
            {
                this.add(new CarParkLot(1));
                this.add(new CarParkLot(2));
                this.add(new CarParkLot(3));
                this.add(new CarParkLot(4));
                this.add(new CarParkLot(5));
            }
        };
        assertEquals(newLots, carPark.getLots());
    }

    @Test
    void shouldGetCountOfLots() {
        assertEquals(carPark.getParkSize(), 5);
    }

    @Test
    void shouldDeleteLotById() {
        List<CarParkLot> newList = carPark.getLots()
                .stream().filter(carParkLot -> carParkLot.getLotId() != 3)
                .collect(Collectors.toList());
        carPark.deleteLot(3);
        assertEquals(carPark.getLots(), newList);
    }

    @Test
    void shouldAddNewLot() {
        List<CarParkLot> newLots = new ArrayList<>(carPark.getLots());
        newLots.add(new CarParkLot(7));
        carPark.addNewLot(new CarParkLot(7));
        assertEquals(newLots, carPark.getLots());
    }
}