package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.team.TeamAlreadyExistsException;
import com.unibuc.tournaments.exception.team.TeamNotCreatedException;
import com.unibuc.tournaments.exception.team.TeamNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.repository.TeamRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) {
        List<Team> existing = getTeamsBy(team.getGameId(), team.getName());
        if (!existing.isEmpty()) {
            throw new TeamAlreadyExistsException();
        }

        Optional<Team> teamOptional;
        try {
            teamOptional = this.teamRepository.createTeam(team);
        } catch (DataIntegrityViolationException e) {
            System.out.println("createTeam() SQL error: " + e.getMessage());
            throw new TeamNotCreatedException();
        }
        if (teamOptional != null && teamOptional.isPresent()) {
            return teamOptional.get();
        } else {
            throw new TeamNotCreatedException();
        }
    }

    public Team getTeam(int id) {
        Optional<Team> teamOptional = teamRepository.getTeam(id);
        if (teamOptional.isPresent()) {
            return teamOptional.get();
        } else {
            throw new TeamNotFoundException();
        }
    }

    public List<Team> getTeamsBy(Integer gameId, String name) {
        return teamRepository.getTeamBy(gameId, name);
    }
}
