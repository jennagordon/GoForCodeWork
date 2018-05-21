package com.sg.service;

public class ProductMaterialNotFoundException extends Exception {

    public ProductMaterialNotFoundException(String message) {
        super(message);
    }

    public ProductMaterialNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
