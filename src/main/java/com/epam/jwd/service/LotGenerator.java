package com.epam.jwd.service;

import com.epam.jwd.repository.CarParkLot;

import java.util.ArrayList;
import java.util.List;

public class LotGenerator {

    public List<CarParkLot> generateLots() {
        return new ArrayList<>() {
            {
                this.add(new CarParkLot(1));
                this.add(new CarParkLot(2));
                this.add(new CarParkLot(3));
                this.add(new CarParkLot(4));
                this.add(new CarParkLot(5));
            }
        };
    }
}
