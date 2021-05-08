package com.unibuc.tournaments.service;


import com.unibuc.tournaments.model.team.Team;
import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(Long id);

    Team save(Team product);

    void deleteById(Long id);
}
