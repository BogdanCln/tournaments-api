package com.unibuc.tournaments.exception;

public class GenericAlreadyExistsException extends RuntimeException {
    public GenericAlreadyExistsException(String entityName) {
        super(entityName + " already exists.");
    }
}
