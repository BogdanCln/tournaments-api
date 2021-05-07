package com.unibuc.tournaments.mapper_orig;

import com.unibuc.tournaments.dto.TournamentRequest;
import com.unibuc.tournaments.dto.TournamentUpdateRequest;
import com.unibuc.tournaments.model.tournament.Tournament;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    public Tournament tournamentUpdateRequestToTournament(TournamentUpdateRequest tournamentRequest) {
        return new Tournament(
                tournamentRequest.getGameId(),
                tournamentRequest.getName(),
                tournamentRequest.getStatus(),
                tournamentRequest.getStartDate(),
                tournamentRequest.getEndDate(),
                tournamentRequest.getLocation(),
                new ArrayList<>()
        );
    }
}
