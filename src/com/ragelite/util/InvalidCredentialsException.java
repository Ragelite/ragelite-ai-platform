package com.ragelite.util;

// Custom checked exception — Exception Handling
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
