package com.unibuc.tournaments.exception.team;

public class TeamNotCreatedException extends RuntimeException {
    public TeamNotCreatedException() {
        super("Team not created.");
    }
}
