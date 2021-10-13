package com.epam.jwd.service;

public class InappropriateValueException extends Exception {
    public InappropriateValueException() {
    }

    public InappropriateValueException(String message) {
        super(message);
    }

    public InappropriateValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InappropriateValueException(Throwable cause) {
        super(cause);
    }

    public InappropriateValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
