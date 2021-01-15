package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.team.Team;
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
            throw new GenericNotCreatedException(Game.class.getName());
        }
    }

    public Game getGame(int id) {
        Optional<Game> gameOptional = gameRepository.getGame(id);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new GenericNotFoundException(Game.class.getName());
        }
    }

    public List<Game> getGamesFiltered(String genre, String publisherName) {
        return gameRepository.getGamesFiltered(genre, publisherName);
    }
}
