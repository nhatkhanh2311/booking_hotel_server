package com.example.hotelbooking.exception;

public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = -2139326462777921841L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
