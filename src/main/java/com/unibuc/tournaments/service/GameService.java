package com.unibuc.tournaments.service;

import com.unibuc.tournaments.model.game.Game;

import java.util.List;

public interface GameService {

    List<Game> findAll();

    Game findById(Long id);

    Game save(Game game);

    void deleteById(Long id);
}
