package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

public class GameControllerTest {
    @Mock
    Model model;

    @Mock
    GameService gameService;

    GameController gameController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        gameController = new GameController(gameService);
    }

    @Captor
    ArgumentCaptor<Game> argumentCaptor;

    @Test
    public void update() {
        Long id = 1L;
        Game gameTest = new Game();
        gameTest.setId(id);

        when(gameService.findById(id)).thenReturn(gameTest);

        String viewName = gameController.update(id.toString(), model);
        Assert.assertEquals("game-form", viewName);
        verify(gameService, times(1)).findById(id);

        verify(model, times(1))
                .addAttribute(eq("game"), argumentCaptor.capture());

        Game gameArg = argumentCaptor.getValue();
        Assert.assertEquals(gameArg.getId(), gameTest.getId());
    }
}
