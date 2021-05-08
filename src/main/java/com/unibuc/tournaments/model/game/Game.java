package com.unibuc.tournaments.model.game;

import com.unibuc.tournaments.model.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String publisherName;

    @Enumerated(value = EnumType.STRING)
    private GameGenre genre;

    @OneToMany(mappedBy = "game")
    private List<Team> teams;
}
