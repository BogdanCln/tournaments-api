package com.unibuc.tournaments.mapper_orig;

import com.unibuc.tournaments.dto.GameRequest;
import com.unibuc.tournaments.model.game.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public Game gameRequestToGame(GameRequest gameRequest) {
        return new Game(
                gameRequest.getName(),
                gameRequest.getPublisherName(),
                gameRequest.getGenre()
        );
    }
}
