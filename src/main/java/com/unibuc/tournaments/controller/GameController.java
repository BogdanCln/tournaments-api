package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String deleteById(@PathVariable String id){
        gameService.deleteById(Long.valueOf(id));
        return "redirect:/game/list";
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
