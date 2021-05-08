package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.tournament.Bracket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BracketRepository extends CrudRepository<Bracket, Long> {
}
