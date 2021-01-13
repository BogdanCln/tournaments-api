package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.team.*;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.repository.TeamMemberRepository;
import com.unibuc.tournaments.repository.TeamRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private TeamMemberRepository teamMemberRepository;

    public TeamService(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
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

    public TeamMember createTeamMember(TeamMember teamMember) {
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.getTeamMember(teamMember.getId());
        if (teamMemberOptional.isPresent()) {
            throw new TeamAlreadyExistsException();
        }

        try {
            teamMemberOptional = this.teamMemberRepository.createTeamMember(teamMember);
        } catch (DataIntegrityViolationException e) {
            System.out.println("createTeamMember() SQL error: " + e.getMessage());
            throw new TeamMemberNotCreatedException();
        }
        if (teamMemberOptional != null && teamMemberOptional.isPresent()) {
            return teamMemberOptional.get();
        } else {
            throw new TeamMemberNotCreatedException();
        }
    }

    public TeamMember getTeamMember(int id) {
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.getTeamMember(id);
        if (teamMemberOptional.isPresent()) {
            return teamMemberOptional.get();
        } else {
            throw new TeamMemberNotFoundException();
        }
    }

    public List<TeamMember> getTeamMembersBy(Integer gameId, String name) {
        return teamMemberRepository.getTeamMembersBy(gameId, name);
    }

}
