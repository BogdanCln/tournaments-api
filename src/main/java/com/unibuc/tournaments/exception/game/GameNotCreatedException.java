package com.unibuc.tournaments.exception.game;

public class GameNotCreatedException extends RuntimeException {
    public GameNotCreatedException() {
        super("Game not created");
    }
}
