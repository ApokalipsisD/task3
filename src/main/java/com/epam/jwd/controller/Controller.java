package com.epam.jwd.controller;

import com.epam.jwd.repository.Car;
import com.epam.jwd.repository.CarPark;
import com.epam.jwd.repository.CarParkLot;
import com.epam.jwd.service.CarParkManager;
import com.epam.jwd.service.LotGenerator;

import java.util.List;

public class Controller {
    private static final int COUNT_OF_CARS = 19;
    private static final CarPark carPark = new CarPark();
    private static final LotGenerator lotGenerator = new LotGenerator();
    private static final CarParkManager carParkManager = new CarParkManager(carPark);

    public static void main(String[] args) {
        List<CarParkLot> list = lotGenerator.generateLots();

        carPark.addToLots(list);
        carParkManager.addToLots();
        for (int i = 0; i < COUNT_OF_CARS; i++) {
            new Car(carParkManager, i + 1).start();
        }
    }
}
