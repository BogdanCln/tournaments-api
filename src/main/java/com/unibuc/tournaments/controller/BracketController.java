package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.service.BracketService;
import com.unibuc.tournaments.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bracket")
public class BracketController {
    private final BracketService bracketService;
    private final TournamentService tournamentService;

    public BracketController(BracketService bracketService, TournamentService tournamentService) {
        this.bracketService = bracketService;
        this.tournamentService = tournamentService;
    }

    @PostMapping()
    public String saveOrUpdate(
            @Valid @ModelAttribute Bracket bracket,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());

            List<Tournament> tournaments = tournamentService.findAll();
            model.addAttribute("tournaments", tournaments);

            return "bracket-form";
        }

        bracketService.save(bracket);
        return "redirect:/bracket/list/" + bracket.getTournament().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        try {
            Long tournamentId = bracketService.findById(Long.valueOf(id)).getTournament().getId();
            bracketService.deleteById(Long.valueOf(id));
            return "redirect:/bracket/list/" + tournamentId;
        } catch (Exception e) {
            throw new GenericNotFoundException(Bracket.class.getSimpleName());
        }
    }

    // Add a bracket (view)
    @GetMapping("/new/{tournamentId}")
    public String create(@PathVariable String tournamentId, Model model) {
        Tournament tournament = tournamentService.findById(Long.valueOf(tournamentId));
        Bracket bracket = new Bracket();
        bracket.setTournament(tournament);
        model.addAttribute("bracket", bracket);

        List<Tournament> tournaments = tournamentService.findAll();
        model.addAttribute("tournaments", tournaments);

        return "bracket-form";
    }

    // Edit a bracket (view)
    @GetMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Bracket bracket = bracketService.findById(Long.valueOf(id));
        model.addAttribute("bracket", bracket);

        List<Tournament> tournaments = tournamentService.findAll();
        model.addAttribute("tournaments", tournaments);

        return "bracket-form";
    }

    @GetMapping("/list/{tournamentId}")
    public ModelAndView bracketsList(@PathVariable String tournamentId) {
        ModelAndView modelAndView = new ModelAndView("brackets");
        List<Bracket> brackets = bracketService.findByTournamentId(Long.valueOf(tournamentId));
        modelAndView.addObject("brackets", brackets);

        Tournament tournament = tournamentService.findById(Long.valueOf(tournamentId));
        modelAndView.addObject("tournament", tournament);

        return modelAndView;
    }
}
