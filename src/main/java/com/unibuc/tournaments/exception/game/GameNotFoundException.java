package com.unibuc.tournaments.exception.game;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Game not found");
    }
}
