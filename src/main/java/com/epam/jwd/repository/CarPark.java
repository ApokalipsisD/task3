package com.epam.jwd.repository;

import java.util.ArrayList;
import java.util.List;

public class CarPark {
    private List<CarParkLot> lots = new ArrayList<>();

    public void addToLots(List<CarParkLot> list) {
        this.lots = list;
    }

    public int getParkSize() {
        return lots.size();
    }

    public List<CarParkLot> getLots() {
        return lots;
    }

    public void addNewLot(CarParkLot lot) {
        if (getParkSize() >= 0) {
            lots.add(lot);
        }
    }

    public void deleteLot(int id) {
        lots.removeIf(lot -> lot.getLotId() == id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarPark carPark = (CarPark) o;

        return lots != null ? lots.equals(carPark.lots) : carPark.lots == null;
    }

    @Override
    public int hashCode() {
        return lots != null ? lots.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "lots=" + lots +
                '}';
    }
}
