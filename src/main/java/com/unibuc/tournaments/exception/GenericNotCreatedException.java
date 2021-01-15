package com.unibuc.tournaments.exception;

public class GenericNotCreatedException extends RuntimeException {
    public GenericNotCreatedException(String entityName) {
        super(entityName + " not created.");
    }
}
