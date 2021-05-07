package com.unibuc.tournaments.model.tournament;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.List;

@Data
@Entity
public class Bracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(mappedBy = "bracket")
    private List<Match> matches;
}
