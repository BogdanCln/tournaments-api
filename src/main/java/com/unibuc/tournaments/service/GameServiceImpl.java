package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        List<Game> games = new LinkedList<>();
        gameRepository.findAll().iterator().forEachRemaining(games::add);
        return games;
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isEmpty()) {
            throw new GenericNotFoundException(Game.class.getSimpleName());
        }
        return gameOptional.get();
    }

    @Override
    public Game save(Game game) {
        try {
            return this.gameRepository.save(game);
        } catch (Exception e) {
            throw new GenericNotCreatedException(Game.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new GenericNotFoundException(Game.class.getSimpleName());
        }

        try {
            gameRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete game " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this game is not permitted.");
        }
    }
}
