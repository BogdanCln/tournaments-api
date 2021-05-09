package com.unibuc.tournaments.service;

import com.unibuc.tournaments.exception.GenericForbiddenException;
import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public List<TeamMember> findAll() {
        List<TeamMember> teamMembers = new LinkedList<>();
        teamMemberRepository.findAll().iterator().forEachRemaining(teamMembers::add);
        return teamMembers;
    }

    @Override
    public TeamMember findById(Long id) {
        Optional<TeamMember> teamOptional = teamMemberRepository.findById(id);
        if (teamOptional.isEmpty()) {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }
        return teamOptional.get();
    }

    @Override
    public List<TeamMember> findByTeamId(Long teamId) {
        List<TeamMember> teamMembers = new LinkedList<>();
        teamMemberRepository.findByTeamId(teamId).iterator().forEachRemaining(teamMembers::add);
        return teamMembers;
    }

    @Override
    public TeamMember save(TeamMember teamMember) {
        try {
            return this.teamMemberRepository.save(teamMember);
        } catch (Exception e) {
            throw new GenericNotCreatedException(TeamMember.class.getSimpleName());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!teamMemberRepository.existsById(id)) {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }

        try {
            teamMemberRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Failed to delete team member " + id);
            e.printStackTrace();

            throw new GenericForbiddenException("Deleting this team member is not permitted.");
        }
    }
}
