package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.service.GameService;
import com.unibuc.tournaments.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private final GameService gameService;

    public TeamController(TeamService teamService, GameService gameService) {
        this.teamService = teamService;
        this.gameService = gameService;
    }

    @GetMapping("/list")
    public ModelAndView teamsList() {
        ModelAndView modelAndView = new ModelAndView("teams");
        List<Team> teams = teamService.findAll();
        modelAndView.addObject("teams", teams);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        teamService.deleteById(Long.valueOf(id));
        return "redirect:/team/list";
    }

    @PostMapping()
    public String saveOrUpdate(
            @Valid @ModelAttribute Team team,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());

            List<Game> games = gameService.findAll();
            model.addAttribute("games", games);
            return "team-form";
        }

        teamService.save(team);
        return "redirect:/team/list";
    }

    // Edit team form
    @RequestMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Team team = teamService.findById(Long.valueOf(id));
        model.addAttribute("team", team);

        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);

        return "team-form";
    }

    // New team form
    @RequestMapping("/new")
    public String create(Model model) {
        model.addAttribute("team", new Team());

        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);

        return "team-form";
    }
}
