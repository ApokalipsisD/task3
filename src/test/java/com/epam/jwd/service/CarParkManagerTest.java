package com.epam.jwd.service;

import com.epam.jwd.repository.Car;
import com.epam.jwd.repository.CarPark;
import com.epam.jwd.repository.CarParkLot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.runAsync;
import static org.junit.jupiter.api.Assertions.*;

class CarParkManagerTest {
    private final CarPark carPark = new CarPark();
    private final CarParkManager carParkManager = new CarParkManager(carPark);

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
    void addToLotsTest() {
        List<CarParkLot> newLots = new LinkedList<>(carPark.getLots());
        carParkManager.addToLots();
        assertEquals(carParkManager.getLots(), newLots);
    }

    @Test
    void shouldGetRightLotAfterExchanging() {
        Runnable task1 = () -> {
            carParkManager.tryChangeLot(new CarParkLot(3), new Car(carParkManager, 1));
        };
        Runnable task2 = () -> {
            carParkManager.tryChangeLot(new CarParkLot(2), new Car(carParkManager, 2));
        };

        CompletableFuture.allOf(runAsync(task1), runAsync(task2)).join();
    }
}