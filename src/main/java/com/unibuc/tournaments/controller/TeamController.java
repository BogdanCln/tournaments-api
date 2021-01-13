package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.dto.TeamRequest;
import com.unibuc.tournaments.mapper.TeamMapper;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private TeamService teamService;
    private TeamMapper teamMapper;

    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping()
    public ResponseEntity<Team> createTeam(
            @Valid
            @RequestBody TeamRequest teamRequest) {
        Team team = teamMapper.teamRequestToTeam(teamRequest);
        Team repositoryTeam = teamService.createTeam(team);
        return ResponseEntity.created(URI.create("/teams/" + team.getId()))
                .body(repositoryTeam);
    }

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable int id) {
        return teamService.getTeam(id);
    }

    @GetMapping()
    public List<Team> getTeamsFiltered(
            @RequestParam(required = false) Integer gameId,
            @RequestParam(required = false) String name) {
        return teamService.getTeamsFiltered(gameId, name);
    }
}
