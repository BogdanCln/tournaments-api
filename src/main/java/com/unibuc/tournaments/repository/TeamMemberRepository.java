package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.team.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
}
