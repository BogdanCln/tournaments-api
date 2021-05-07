package com.unibuc.tournaments.model.team;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.tournament.Tournament;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> members;

    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments;
}
