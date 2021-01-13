package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.game.GameNotCreatedException;
import com.unibuc.tournaments.exception.game.GameNotFoundException;
import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Game game) {
        Optional<Game> gameOptional = this.gameRepository.createGame(game);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new GameNotCreatedException();
        }
    }

    public Game getGame(int id) {
        Optional<Game> gameOptional = gameRepository.getGame(id);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new GameNotFoundException();
        }
    }

    public List<Game> getGamesFiltered(String genre, String publisherName) {
        return gameRepository.getGamesFiltered(genre, publisherName);
    }
}
