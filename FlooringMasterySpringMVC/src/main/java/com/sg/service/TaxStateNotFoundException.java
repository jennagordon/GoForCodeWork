package com.sg.service;

public class TaxStateNotFoundException extends Exception {

    public TaxStateNotFoundException(String message) {
        super(message);
    }

    public TaxStateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
