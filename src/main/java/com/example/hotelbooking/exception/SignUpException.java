package com.example.hotelbooking.exception;

public class SignUpException extends RuntimeException {
    public SignUpException(String errorMessage) {
        super(errorMessage);
    }
}
