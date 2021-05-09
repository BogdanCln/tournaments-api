package com.unibuc.tournaments.service;


import com.unibuc.tournaments.model.tournament.Tournament;

import java.util.List;

public interface TournamentService {

    List<Tournament> findAll();

    Tournament findById(Long id);

    Tournament save(Tournament tournament);

    void deleteById(Long id);
}
