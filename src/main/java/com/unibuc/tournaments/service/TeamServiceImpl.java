package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = new LinkedList<>();
        teamRepository.findAll().iterator().forEachRemaining(teams::add);
        return teams;
    }

    @Override
    public Team findById(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isEmpty()) {
            throw new GenericNotFoundException(Team.class.getSimpleName());
        }
        return teamOptional.get();
    }

    @Override
    public Team save(Team team) {
        try {
            return this.teamRepository.save(team);
        } catch (Exception e) {
            throw new GenericNotCreatedException(Team.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new GenericNotFoundException(Team.class.getSimpleName());
        }

        try {
            teamRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete team " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this team is not permitted.");
        }
    }
}
