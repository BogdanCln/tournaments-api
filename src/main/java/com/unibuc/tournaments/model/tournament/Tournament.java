package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.team.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Game game;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private TournamentStatus status;

    private Date startDate;
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
