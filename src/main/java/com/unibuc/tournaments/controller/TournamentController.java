package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.dto.TournamentRequest;
import com.unibuc.tournaments.mapper.TournamentMapper;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.service.TournamentService;
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

    public TournamentController(TournamentService tournamentService, TournamentMapper tournamentMapper) {
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
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

    @PostMapping()
    public ResponseEntity<Tournament> createTournament(
            @Valid
            @RequestBody TournamentRequest tournamentRequest) {
        Tournament tournament = tournamentMapper.tournamentRequestToTournament(tournamentRequest);
        Tournament createdTournament = tournamentService.createTournament(tournament);
        return ResponseEntity.created(URI.create("/tournaments/" + createdTournament.getId()))
                .body(createdTournament);
    }
}
