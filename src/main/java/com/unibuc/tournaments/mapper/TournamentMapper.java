package com.unibuc.tournaments.mapper;

import com.unibuc.tournaments.dto.TournamentRequest;
import com.unibuc.tournaments.model.tournament.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapper {
    public Tournament tournamentRequestToTournament(TournamentRequest tournamentRequest) {
        return new Tournament(
                tournamentRequest.getGameId(),
                tournamentRequest.getName(),
                tournamentRequest.getStatus(),
                tournamentRequest.getStartDate(),
                tournamentRequest.getEndDate(),
                tournamentRequest.getLocation(),
                tournamentRequest.getTeams()
        );
    }
}
