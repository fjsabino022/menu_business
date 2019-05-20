package com.fsabino.menu_business.exception;

public class MenuNotFoundException extends Exception {

    public MenuNotFoundException(String message) {
        super(message);
    }

    public MenuNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
