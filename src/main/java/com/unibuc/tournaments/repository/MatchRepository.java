package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.tournament.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
}
