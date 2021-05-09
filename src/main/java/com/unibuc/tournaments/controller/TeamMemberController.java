package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberType;
import com.unibuc.tournaments.service.TeamMemberService;
import com.unibuc.tournaments.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/team-member")
public class TeamMemberController {
    private final TeamMemberService teamMemberService;
    private final TeamService teamService;

    public TeamMemberController(TeamMemberService teamMemberService, TeamService teamService) {
        this.teamMemberService = teamMemberService;
        this.teamService = teamService;
    }

    @PostMapping()
    public String saveOrUpdate(
            @Valid @ModelAttribute TeamMember teamMember,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());

            List<Team> teams = teamService.findAll();
            model.addAttribute("teams", teams);

            return "team-member-form";
        }

        teamMemberService.save(teamMember);
        return "redirect:/team-member/list/" + teamMember.getTeam().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        try {
            Long teamId = teamMemberService.findById(Long.valueOf(id)).getTeam().getId();
            teamMemberService.deleteById(Long.valueOf(id));
            return "redirect:/team-member/list/" + teamId;
        } catch (Exception e) {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }
    }

    // Add a team member view
    @GetMapping("/new/{teamId}")
    public String create(@PathVariable String teamId, Model model) {
        Team team = teamService.findById(Long.valueOf(teamId));
        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(team);
        model.addAttribute("teamMember", teamMember);

        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);

        return "team-member-form";
    }

    // Edit a team member view
    @GetMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        TeamMember teamMember = teamMemberService.findById(Long.valueOf(id));
        model.addAttribute("teamMember", teamMember);

        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);

        return "team-member-form";
    }

    @GetMapping("/list/{teamId}")
    public ModelAndView teamMembersList(@PathVariable String teamId) {
        ModelAndView modelAndView = new ModelAndView("team-members");
        List<TeamMember> teamMembers = teamMemberService.findByTeamId(Long.valueOf(teamId));
        modelAndView.addObject("teamMembers", teamMembers);

        Team team = teamService.findById(Long.valueOf(teamId));
        modelAndView.addObject("team", team);

        return modelAndView;
    }
}
