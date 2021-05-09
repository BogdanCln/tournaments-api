package com.unibuc.tournaments.service;

import com.unibuc.tournaments.model.tournament.Bracket;

import java.util.List;

public interface BracketService {

    List<Bracket> findAll();

    Bracket findById(Long id);

    List<Bracket> findByTournamentId(Long tournamentId);

    Bracket save(Bracket bracket);

    void deleteById(Long id);
}
