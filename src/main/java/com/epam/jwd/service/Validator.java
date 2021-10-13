package com.epam.jwd.service;

import com.epam.jwd.repository.CarParkLot;

public class Validator {
    private static final String LOT_IS_NULL = "Lot is null";

    public static void isNull(CarParkLot lot) throws InappropriateValueException {
        if (lot == null) {
            throw new InappropriateValueException(LOT_IS_NULL);
        }
    }
}
