package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.tournament.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
