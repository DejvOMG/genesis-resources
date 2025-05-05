package com.genesis.exception;

public class PersonIdAlreadyExistsException extends RuntimeException {
    public PersonIdAlreadyExistsException(String message) {
        super(message);
    }
}
