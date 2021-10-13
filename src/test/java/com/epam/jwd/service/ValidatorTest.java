package com.epam.jwd.service;

import com.epam.jwd.repository.CarParkLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private CarParkLot carParkLot;

    @Test
    void shouldThrowNullPointerExceptionIfLotIsNull() {
        assertThrows(InappropriateValueException.class, () -> {
            Validator.isNull(carParkLot);
        });
    }
}