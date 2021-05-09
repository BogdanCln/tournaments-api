package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.service.BracketService;
import com.unibuc.tournaments.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;
    private final BracketService bracketService;

    public MatchController(MatchService matchService, BracketService bracketService) {
        this.matchService = matchService;
        this.bracketService = bracketService;
    }

    @PostMapping()
    public String saveOrUpdate(
            @Valid @ModelAttribute Match match,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());

            try {
                List<Bracket> brackets = match.getBracket().getTournament().getBrackets();
                model.addAttribute("brackets", brackets);

                List<Team> tournamentTeams = match.getBracket().getTournament().getTeams();
                model.addAttribute("tournamentTeams", tournamentTeams);
            } catch (Exception e) {
                return "redirect:/tournaments/list/";
            }

            return "match-form";
        }

        matchService.save(match);
        return "redirect:/match/list/" + match.getBracket().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        try {
            Long bracketId = matchService.findById(Long.valueOf(id)).getBracket().getId();
            matchService.deleteById(Long.valueOf(id));
            return "redirect:/match/list/" + bracketId;
        } catch (Exception e) {
            throw new GenericNotFoundException(Match.class.getSimpleName());
        }
    }

    // Add a match (view)
    @GetMapping("/new/{bracketId}")
    public String create(@PathVariable String bracketId, Model model) {
        Bracket bracket = bracketService.findById(Long.valueOf(bracketId));
        Match match = new Match();
        match.setBracket(bracket);
        model.addAttribute("match", match);

        List<Bracket> brackets = bracket.getTournament().getBrackets();
        model.addAttribute("brackets", brackets);

        List<Team> tournamentTeams = bracket.getTournament().getTeams();
        model.addAttribute("tournamentTeams", tournamentTeams);

        return "match-form";
    }

    // Edit a match (view)
    @GetMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Match match = matchService.findById(Long.valueOf(id));
        model.addAttribute("match", match);

        List<Bracket> brackets = match.getBracket().getTournament().getBrackets();
        model.addAttribute("brackets", brackets);

        List<Team> tournamentTeams = match.getBracket().getTournament().getTeams();
        model.addAttribute("tournamentTeams", tournamentTeams);

        return "match-form";
    }

    @GetMapping("/list/{bracketId}")
    public ModelAndView matchesList(@PathVariable String bracketId) {
        ModelAndView modelAndView = new ModelAndView("matches");

        List<Match> matches = matchService.findByBracketId(Long.valueOf(bracketId));
        modelAndView.addObject("matches", matches);

        Bracket bracket = bracketService.findById(Long.valueOf(bracketId));
        modelAndView.addObject("bracket", bracket);

        return modelAndView;
    }
}
