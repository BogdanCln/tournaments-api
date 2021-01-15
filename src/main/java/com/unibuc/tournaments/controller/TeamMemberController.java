package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.dto.TeamMemberCategoryRequest;
import com.unibuc.tournaments.dto.TeamMemberRequest;
import com.unibuc.tournaments.mapper.TeamMapper;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberCategory;
import com.unibuc.tournaments.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
public class TeamMemberController {
    private TeamService teamService;
    private TeamMapper teamMapper;

    public TeamMemberController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping()
    public ResponseEntity<TeamMember> createMember(
            @Valid
            @RequestBody TeamMemberRequest memberRequest) {
        TeamMember teamMember = teamMapper.teamMemberRequestToTeamMember(memberRequest);
        TeamMember repositoryTeam = teamService.createTeamMember(teamMember);
        return ResponseEntity.created(URI.create("/teams/members/" + teamMember.getId()))
                .body(repositoryTeam);
    }

    @GetMapping("/{id}")
    public TeamMember getTeamMember(@PathVariable int id) {
        return teamService.getTeamMember(id);
    }

    @GetMapping()
    public List<TeamMember> getTeamMembersFiltered(
            @RequestParam(required = false) Integer teamId,
            @RequestParam(required = false) String type
    ) {
        return teamService.getTeamMembersFiltered(teamId, type);
    }

    @PostMapping("/categories")
    public ResponseEntity<TeamMember> createCategory(
            @Valid
            @RequestBody TeamMemberCategoryRequest requestBody) {
        TeamMemberCategory teamMemberCategory = teamMapper.teamMemberCategoryRequestToTeamMemberCategory(requestBody);
        TeamMember updatedMember = teamService.createTeamMemberCategory(teamMemberCategory);
        return ResponseEntity.created(URI.create("/teams/members/" + updatedMember.getId()))
                .body(updatedMember);
    }
}
