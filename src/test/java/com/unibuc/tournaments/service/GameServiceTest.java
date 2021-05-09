package com.unibuc.tournaments.service;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.game.GameGenre;
import com.unibuc.tournaments.repository.GameRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        gameService = new GameServiceImpl(gameRepository);
    }

    @Test
    public void findGames() {
        List<Game> gamesRet = new ArrayList<>();

        Game game = new Game();
        game.setId(1L);
        game.setGenre(GameGenre.FPS);
        game.setName("Test");
        game.setPublisherName("Test PN");

        gamesRet.add(game);

        when(gameRepository.findAll()).thenReturn(gamesRet);
        List<Game> games = gameService.findAll();
        assertEquals(games.size(), 1);
        verify(gameRepository, times(1)).findAll();
    }
}
