package com.unibuc.tournaments.model.team;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.tournament.Tournament;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> members;

    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments;
}
