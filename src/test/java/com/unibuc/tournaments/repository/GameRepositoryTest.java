package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Slf4j
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void findGames() {
        List<Game> games = new LinkedList<>();
        gameRepository.findAll().iterator().forEachRemaining(games::add);
        assertEquals(2, games.size());
    }
}
