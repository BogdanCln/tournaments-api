package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.team.Team;
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
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void findTeams() {
        List<Team> teams = new LinkedList<>();
        teamRepository.findAll().iterator().forEachRemaining(teams::add);
        assertEquals(4, teams.size());
    }
}
