package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.team.TeamNotCreatedException;
import com.unibuc.tournaments.exception.team.TeamNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) {
        Optional<Team> teamOptional = this.teamRepository.createTeam(team);
        if (teamOptional.isPresent()) {
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

    public List<Team> getTeamsBy(String gameId) {
        return teamRepository.getTeamBy(gameId);
    }
}
