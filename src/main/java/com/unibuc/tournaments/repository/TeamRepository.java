package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.team.Team;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
}
