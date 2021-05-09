package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.tournament.Bracket;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Slf4j
public class BracketRepositoryTest {
    @Autowired
    private BracketRepository bracketRepository;

    @Test
    public void findByTournamentId() {
        List<Bracket> brackets = bracketRepository.findByTournamentId(2L);
        assertEquals(2, brackets.size());
    }
}
