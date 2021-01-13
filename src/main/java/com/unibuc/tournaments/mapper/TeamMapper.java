package com.unibuc.tournaments.mapper;

import com.unibuc.tournaments.dto.TeamMemberCategoryRequest;
import com.unibuc.tournaments.dto.TeamMemberRequest;
import com.unibuc.tournaments.dto.TeamRequest;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberCategory;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public Team teamRequestToTeam(TeamRequest teamRequest) {
        return new Team(
                teamRequest.getGameId(),
                teamRequest.getName()
        );
    }

    public TeamMember teamMemberRequestToTeamMember(TeamMemberRequest memberRequest) {
        return new TeamMember(memberRequest.getTeamId(), memberRequest.getType(), memberRequest.getFirstName(), memberRequest.getLastName(), memberRequest.getNickName(), memberRequest.getDateOfBirth());
    }

    public TeamMemberCategory teamMemberCategoryRequestToTeamMemberCategory(TeamMemberCategoryRequest teamMemberCategoryRequest) {
        return new TeamMemberCategory(teamMemberCategoryRequest.getMemberId(), teamMemberCategoryRequest.getName());
    }
}
