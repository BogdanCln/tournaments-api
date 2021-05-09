package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.team.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Setter
@Getter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private TournamentStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String location;

    @ManyToMany
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id", referencedColumnName = "id"))
    private List<Team> teams;

    @OneToMany(mappedBy = "tournament")
    List<Bracket> brackets;
}
