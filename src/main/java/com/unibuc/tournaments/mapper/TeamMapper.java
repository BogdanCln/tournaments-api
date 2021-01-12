package com.unibuc.tournaments.mapper;

import com.unibuc.tournaments.dto.TeamRequest;
import com.unibuc.tournaments.model.team.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public Team teamRequestToTeam(TeamRequest teamRequest) {
        return new Team(
                teamRequest.getGameId(),
                teamRequest.getName()
        );
    }
}
