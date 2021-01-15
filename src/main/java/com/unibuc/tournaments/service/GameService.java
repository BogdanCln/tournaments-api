package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
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
            throw new GenericNotCreatedException(Game.class.getSimpleName());
        }
    }

    public Game updateGame(int id, Game newGame) {
        Optional<Game> existingGame = gameRepository.getGame(id);
        if (existingGame.isEmpty()) {
            throw new GenericNotFoundException(Game.class.getSimpleName());
        }

        newGame.setId(existingGame.get().getId());

        if (newGame.getName() == null) {
            newGame.setName(existingGame.get().getName());
        }
        if (newGame.getPublisherName() == null) {
            newGame.setPublisherName(existingGame.get().getPublisherName());
        }
        if (newGame.getGenre() == null) {
            newGame.setGenre(existingGame.get().getGenre());
        }

        Optional<Game> gameOptional = this.gameRepository.updateGame(newGame);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new GenericNotCreatedException(Game.class.getSimpleName());
        }
    }

    public Game getGame(int id) {
        Optional<Game> gameOptional = gameRepository.getGame(id);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new GenericNotFoundException(Game.class.getSimpleName());
        }
    }

    public List<Game> getGamesFiltered(String genre, String publisherName) {
        return gameRepository.getGamesFiltered(genre, publisherName);
    }

    public void deleteGame(int id) {
        if (gameRepository.getGame(id).isEmpty()) {
            throw new GenericNotFoundException(Game.class.getSimpleName());
        }

        try {
            gameRepository.deleteGame(id);
        } catch (Exception e) {
            System.out.println("Failed to delete game " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this game is not permitted.");
        }
    }
}
