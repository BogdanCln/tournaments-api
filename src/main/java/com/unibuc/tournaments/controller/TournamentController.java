package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.dto.GameRequest;
import com.unibuc.tournaments.dto.MatchUpdateRequest;
import com.unibuc.tournaments.dto.TournamentRequest;
import com.unibuc.tournaments.dto.TournamentUpdateRequest;
import com.unibuc.tournaments.mapper.MatchMapper;
import com.unibuc.tournaments.mapper.TournamentMapper;
import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    private TournamentService tournamentService;
    private TournamentMapper tournamentMapper;
    private MatchMapper matchMapper;

    public TournamentController(TournamentService tournamentService, TournamentMapper tournamentMapper, MatchMapper matchMapper) {
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
        this.matchMapper = matchMapper;
    }

    @PostMapping()
    public ResponseEntity<Tournament> createTournament(
            @Valid
            @RequestBody TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentMapper.tournamentRequestToTournament(tournamentRequest);
        Tournament createdTournament = tournamentService.createTournament(tournament);
        return ResponseEntity.created(URI.create("/tournaments/" + createdTournament.getId()))
                .body(createdTournament);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTournament(@PathVariable int id) {
        tournamentService.deleteTournament(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTournament(
            @PathVariable int id,
            @Valid
            @RequestBody TournamentUpdateRequest tournamentRequest) {

        Tournament tournament = tournamentMapper.tournamentUpdateRequestToTournament(tournamentRequest);
        Tournament repositoryTournament = tournamentService.updateTournament(id, tournament);
        return ResponseEntity.created(URI.create("/tournaments/" + tournament.getId()))
                .body(repositoryTournament);
    }

    @GetMapping()
    public List<Tournament> getTournamentsFiltered(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer gameId) {
        return tournamentService.getTournamentsFiltered(name, gameId);
    }

    @GetMapping("/{id}")
    public Tournament getTournament(@PathVariable int id) {
        return tournamentService.getTournament(id);
    }

    @GetMapping("/{id}/bracket")
    public Bracket getTournamentBracket(@PathVariable int id) {
        return tournamentService.getTournamentBracket(id);
    }

    @PutMapping("/{tournamentId}/bracket/match/{matchId}")
    public ResponseEntity updateMatch(
            @PathVariable int tournamentId,
            @PathVariable int matchId,
            @Valid
            @RequestBody MatchUpdateRequest matchRequest) {

        Match match = matchMapper.matchUpdateRequestToMatch(matchRequest);
        Match repositoryMatch = tournamentService.updateMatch(matchId, match);
        return ResponseEntity.created(URI.create("/tournaments/" + tournamentId + "/bracket"))
                .body(repositoryMatch);
    }
}
