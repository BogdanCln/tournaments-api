package com.unibuc.tournaments.controller;

import com.unibuc.tournaments.model.game.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping()
    public String newFilm() {
        return "home";
    }
}
