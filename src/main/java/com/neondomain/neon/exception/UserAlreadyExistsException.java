package com.neondomain.neon.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
