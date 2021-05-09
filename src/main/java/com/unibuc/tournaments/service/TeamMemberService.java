package com.unibuc.tournaments.service;


import com.unibuc.tournaments.model.team.TeamMember;

import java.util.List;

public interface TeamMemberService {

    List<TeamMember> findAll();

    TeamMember findById(Long id);

    List<TeamMember> findByTeamId(Long teamId);

    TeamMember save(TeamMember product);

    void deleteById(Long id);
}
