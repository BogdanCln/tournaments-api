package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.dto.GameRequest;
import com.unibuc.tournaments.mapper.GameMapper;
import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private GameService gameService;
    private GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping()
    public List<Game> getGamesFiltered(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String publisherName) {
        return gameService.getGamesFiltered(genre, publisherName);
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable int id) {
        return gameService.getGame(id);
    }

    @PostMapping()
    public ResponseEntity<Game> createGame(
            @Valid
            @RequestBody GameRequest gameRequest) {
        Game game = gameMapper.gameRequestToGame(gameRequest);
        Game repositoryGame = gameService.createGame(game);
        return ResponseEntity.created(URI.create("/games/" + game.getId()))
                .body(repositoryGame);
    }

}
