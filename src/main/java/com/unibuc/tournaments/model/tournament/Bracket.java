package com.unibuc.tournaments.model.tournament;

import java.util.Map;
import java.util.List;

public class Bracket {
    private int id;
    private int tournamentId;
    Map<String, List<Match>> phases;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Map<String, List<Match>> getPhases() {
        return phases;
    }

    public void setPhases(Map<String, List<Match>> phases) {
        this.phases = phases;
    }

    public Bracket(int id, int tournamentId) {
        this.id = id;
        this.tournamentId = tournamentId;
    }

    public Bracket(int tournamentId) {
        this.tournamentId = tournamentId;
    }
}
