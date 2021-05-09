package com.unibuc.tournaments.service;


import com.unibuc.tournaments.model.tournament.Match;

import java.util.List;

public interface MatchService {

    List<Match> findAll();

    Match findById(Long id);

    List<Match> findByBracketId(Long bracketId);

    Match save(Match match);

    void deleteById(Long id);
}
