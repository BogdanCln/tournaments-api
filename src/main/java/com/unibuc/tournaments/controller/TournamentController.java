package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.service.GameService;
import com.unibuc.tournaments.service.TeamService;
import com.unibuc.tournaments.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final TeamService teamService;
    private final GameService gameService;

    public TournamentController(
            TournamentService tournamentService,
            TeamService teamService,
            GameService gameService) {
        this.tournamentService = tournamentService;
        this.teamService = teamService;
        this.gameService = gameService;
    }

    @GetMapping("/list")
    public ModelAndView tournamentsList() {
        ModelAndView modelAndView = new ModelAndView("tournaments");
        List<Tournament> tournaments = tournamentService.findAll();
        modelAndView.addObject("tournaments", tournaments);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        tournamentService.deleteById(Long.valueOf(id));
        return "redirect:/tournament/list";
    }

    @PostMapping()
    public String saveOrUpdate(
            @Valid @ModelAttribute Tournament tournament,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());

            List<Game> games = gameService.findAll();
            model.addAttribute("games", games);

            List<Team> allTeams = teamService.findAll();
            model.addAttribute("allTeams", allTeams);

            return "tournament-form";
        }

        tournamentService.save(tournament);
        return "redirect:/tournament/list";
    }

    // Edit tournament form
    @RequestMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Tournament tournament = tournamentService.findById(Long.valueOf(id));
        model.addAttribute("tournament", tournament);

        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);

        List<Team> allTeams = teamService.findAll();
        model.addAttribute("allTeams", allTeams);

        return "tournament-form";
    }

    // New tournament form
    @RequestMapping("/new")
    public String create(Model model) {
        model.addAttribute("tournament", new Tournament());

        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);

        List<Team> allTeams = teamService.findAll();
        model.addAttribute("allTeams", allTeams);

        return "tournament-form";
    }
}
