package com.unibuc.tournaments.model.tournament;

import java.util.Dictionary;
import java.util.List;

public class Bracket {
    private int id;
    private int tournamentId;
    Dictionary<String, List<Match>> phases;
}
