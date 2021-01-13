package com.unibuc.tournaments.exception.team;

public class TeamAlreadyExistsException extends RuntimeException {
    public TeamAlreadyExistsException() {
        super("Team already exists.");
    }
}
