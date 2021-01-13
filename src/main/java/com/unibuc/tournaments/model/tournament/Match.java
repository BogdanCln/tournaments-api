package com.unibuc.tournaments.model.tournament;

import java.util.Date;
import java.util.Dictionary;

public class Match {
    private int id;
    private int redTeamId;
    private int blueTeamId;
    private int redTeamScore;
    private int blueTeamScore;
    private Date scheduledDate;
    private MatchStatus status;
    private Boolean defaultWin;
    private Dictionary<String, String> customFields;
}
