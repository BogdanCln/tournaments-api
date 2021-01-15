package com.unibuc.tournaments.exception;

public class GenericNotFoundException extends RuntimeException {
    public GenericNotFoundException(String entityName) {
        super(entityName + " not found.");
    }
}
