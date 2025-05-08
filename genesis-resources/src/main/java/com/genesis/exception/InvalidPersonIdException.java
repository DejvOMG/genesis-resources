package com.genesis.exception;

public class InvalidPersonIdException extends RuntimeException {
    public InvalidPersonIdException(String message) {
        super(message);
    }
}
