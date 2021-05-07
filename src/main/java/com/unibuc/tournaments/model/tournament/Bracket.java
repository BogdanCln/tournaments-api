package com.unibuc.tournaments.model.tournament;

import lombok.Data;

import java.util.Map;
import java.util.List;

@Data()
public class Bracket {
    private int id;
    private int tournamentId;
    Map<String, List<Match>> phases;

    public Bracket(int id, int tournamentId) {
        this.id = id;
        this.tournamentId = tournamentId;
    }

    public Bracket(int tournamentId) {
        this.tournamentId = tournamentId;
    }
}
