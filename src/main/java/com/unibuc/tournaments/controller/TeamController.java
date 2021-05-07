package com.unibuc.tournaments.controller_orig;

import com.unibuc.tournaments.dto.TeamRequest;
import com.unibuc.tournaments.mapper_orig.TeamMapper;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.service_orig.TeamService;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/{id}")
    public ResponseEntity updateTeam(
            @PathVariable int id,
            @Valid
            @RequestBody TeamRequest teamRequest) {

        Team team = teamMapper.teamRequestToTeam(teamRequest);
        Team repositoryTeam = teamService.updateTeam(id, team);
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeam(@PathVariable int id) {
        teamService.deleteTeam(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success");
    }

    @DeleteMapping("/{id}/members")
    public ResponseEntity deleteTeamMembers(@PathVariable int id) {
        teamService.deleteAllTeamMembers(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Success");
    }
}
