package com.unibuc.tournaments.exception.team;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
        super("Team not found.");
    }
}
