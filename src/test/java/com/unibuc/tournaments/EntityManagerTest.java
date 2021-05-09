package com.unibuc.tournaments;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.game.GameGenre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    public void findGame() {
        Game gameFound = entityManager.find(Game.class, 1L);
        assertEquals(gameFound.getName(), "CS:GO");
    }

    @Test
    public void updateGame() {
        Game gameFound = entityManager.find(Game.class, 1L);
        gameFound.setGenre(GameGenre.MOBA);

        entityManager.persist(gameFound);
        entityManager.flush();
    }

    @Test
    public void findGenre() {
        Game gameFound = entityManager.find(Game.class, 1L);
        assertEquals(gameFound.getGenre(), GameGenre.MOBA);
    }
}
