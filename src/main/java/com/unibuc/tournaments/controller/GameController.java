package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/list")
    public ModelAndView gamesList() {
        ModelAndView modelAndView = new ModelAndView("games");
        List<Game> games = gameService.findAll();
        modelAndView.addObject("games", games);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        gameService.deleteById(Long.valueOf(id));
        return "redirect:/game/list";
    }

    @PostMapping()
    public String createOrUpdate(
            @Valid @ModelAttribute Game game,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors count: " + bindingResult.getErrorCount());
            return "game-form";
        }

        System.out.println("Game name: " + game.getName());

        gameService.save(game);
        return "redirect:/game/list";
    }

    // Edit game form
    @RequestMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Game game = gameService.findById(Long.valueOf(id));
        model.addAttribute("game", game);
        return "game-form";
    }

    // New game form
    @RequestMapping("/new")
    public String create(Model model) {
        model.addAttribute("game", new Game());
        return "game-form";
    }
}
