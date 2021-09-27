package com.epam.jwd.repository;

import com.epam.jwd.service.CarParkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private final CarPark carPark = new CarPark();
    private final CarParkManager carParkManager = new CarParkManager(carPark);
    private final Car car = new Car(carParkManager, 15);

    @Test
    void shouldGetCarId() {
        car.setCarId(288);
        assertEquals(car.getCarId(), 288);
    }
}