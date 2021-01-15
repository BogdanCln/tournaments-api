package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericAlreadyExistsException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberCategory;
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
        List<Team> existing = getTeamsFiltered(team.getGameId(), team.getName());
        if (!existing.isEmpty()) {
            throw new GenericAlreadyExistsException(Team.class.getSimpleName());
        }

        Optional<Team> teamOptional;
        try {
            teamOptional = this.teamRepository.createTeam(team);
        } catch (DataIntegrityViolationException e) {
            System.out.println("createTeam() SQL error: " + e.getMessage());
            throw new GenericNotCreatedException(Team.class.getSimpleName());

        }
        if (teamOptional != null && teamOptional.isPresent()) {
            return teamOptional.get();
        } else {
            throw new GenericNotCreatedException(Team.class.getSimpleName());
        }
    }

    public Team getTeam(int id) {
        Optional<Team> teamOptional = teamRepository.getTeam(id);
        if (teamOptional.isPresent()) {
            return teamOptional.get();
        } else {
            throw new GenericNotFoundException(Team.class.getSimpleName());
        }
    }

    public List<Team> getTeamsFiltered(Integer gameId, String name) {
        return teamRepository.getTeamsFiltered(gameId, name);
    }

    public TeamMember createTeamMember(TeamMember teamMember) {
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.getTeamMember(teamMember.getId());
        if (teamMemberOptional.isPresent()) {
            throw new GenericAlreadyExistsException(Team.class.getSimpleName());
        }

        try {
            teamMemberOptional = this.teamMemberRepository.createTeamMember(teamMember);
        } catch (DataIntegrityViolationException e) {
            System.out.println("createTeamMember() SQL error: " + e.getMessage());
            throw new GenericNotCreatedException(TeamMember.class.getSimpleName());
        }
        if (teamMemberOptional != null && teamMemberOptional.isPresent()) {
            return teamMemberOptional.get();
        } else {
            throw new GenericNotCreatedException(TeamMember.class.getSimpleName());
        }
    }

    public TeamMember getTeamMember(int id) {
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.getTeamMember(id);
        if (teamMemberOptional.isPresent()) {
            return teamMemberOptional.get();
        } else {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }
    }

    public List<TeamMember> getTeamMembersFiltered(Integer gameId, String name) {
        return teamMemberRepository.getTeamMembersFiltered(gameId, name);
    }

    public TeamMember createTeamMemberCategory(TeamMemberCategory teamMemberCategory) {
        Optional<TeamMember> member = teamMemberRepository.getTeamMember(teamMemberCategory.getMemberId());
        if (member.isEmpty()) {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }

        try {
            member = this.teamMemberRepository.createTeamMemberCategory(teamMemberCategory);
        } catch (DataIntegrityViolationException e) {
            System.out.println("createTeamMemberCategory() SQL error: " + e.getMessage());
            throw new GenericNotCreatedException(TeamMemberCategory.class.getSimpleName());
        }
        if (member != null && member.isPresent()) {
            return member.get();
        } else {
            throw new GenericNotCreatedException(TeamMemberCategory.class.getSimpleName());
        }
    }
}
