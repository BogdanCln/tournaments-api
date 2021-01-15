package com.unibuc.tournaments.mapper;

import com.unibuc.tournaments.dto.MatchUpdateRequest;
import com.unibuc.tournaments.model.tournament.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    public Match matchUpdateRequestToMatch(MatchUpdateRequest matchUpdateRequest) {
        return new Match(
                matchUpdateRequest.getBestOf(),
                matchUpdateRequest.getRedTeamScore(),
                matchUpdateRequest.getBlueTeamScore(),
                matchUpdateRequest.getStatus(),
                matchUpdateRequest.getScheduledDate(),
                matchUpdateRequest.getDefaultWin()
        );
    }
}
