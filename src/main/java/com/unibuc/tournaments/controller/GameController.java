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
    public String saveOrUpdate(
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

    // New game form
    @RequestMapping("/edit/{id}")
    public String newFilm(@PathVariable String id, Model model) {
        Game game = gameService.findById(Long.valueOf(id));
        model.addAttribute("game", game);
        return "game-form";
    }

    // New game form
    @RequestMapping("/new")
    public String newFilm(Model model) {
        model.addAttribute("game", new Game());
        return "game-form";
    }

//    @GetMapping()
//    public List<Game> getGamesFiltered(
//            @RequestParam(required = false) String genre,
//            @RequestParam(required = false) String publisherName) {
//        return gameService.getGamesFiltered(genre, publisherName);
//    }

//    @GetMapping("/{id}")
//    public Game getGame(@PathVariable int id) {
//        return gameService.getGame(id);
//    }
//
//    @PostMapping()
//    public ResponseEntity<Game> createGame(
//            @Valid
//            @RequestBody GameRequest gameRequest) {
//        Game game = gameMapper.gameRequestToGame(gameRequest);
//        Game repositoryGame = gameService.createGame(game);
//        return ResponseEntity.created(URI.create("/games/" + game.getId()))
//                .body(repositoryGame);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteGame(@PathVariable int id) {
//        gameService.deleteGame(id);
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .body("Success");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity updateGame(
//            @PathVariable int id,
//            @Valid
//            @RequestBody GameRequest gameRequest) {
//
//        Game game = gameMapper.gameRequestToGame(gameRequest);
//        Game repositoryGame = gameService.updateGame(id, game);
//        return ResponseEntity.created(URI.create("/games/" + game.getId()))
//                .body(repositoryGame);
//    }
}
