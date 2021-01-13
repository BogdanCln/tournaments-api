package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.team.Team;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class Tournament {
    private int id;
    private String name;
    private List<Team> teams;
    private Bracket bracket;
    private TournamentStatus status;
    private Date startDate;
    private Date endDate;
    private String location;
    private Dictionary<String, String> customFields;
}
