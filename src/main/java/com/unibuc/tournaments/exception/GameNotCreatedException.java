package com.unibuc.tournaments.exception;

public class GameNotCreatedException extends RuntimeException {
    public GameNotCreatedException() {
        super("Game not created");
    }
}
