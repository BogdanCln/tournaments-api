package com.unibuc.tournaments.model.tournament;

import lombok.Data;

import java.util.Date;
import java.util.Dictionary;

@Data()
public class Match {
    private int id;
    private Integer bracketId;
    private String bracketPhase;
    private Integer redTeamId;
    private Integer blueTeamId;
    private Integer bestOf;
    private Integer redTeamScore;
    private Integer blueTeamScore;
    private MatchStatus status;
    private Date scheduledDate;
    private Boolean defaultWin;
    private Dictionary<String, String> customFields;

    public Match(int id, Integer bracketId, String bracketPhase, Integer redTeamId, Integer blueTeamId, Integer bestOf, Integer redTeamScore, Integer blueTeamScore, MatchStatus status, Date scheduledDate, Boolean defaultWin) {
        this.id = id;
        this.bracketId = bracketId;
        this.bracketPhase = bracketPhase;
        this.redTeamId = redTeamId;
        this.blueTeamId = blueTeamId;
        this.bestOf = bestOf;
        this.redTeamScore = redTeamScore;
        this.blueTeamScore = blueTeamScore;
        this.status = status;
        this.scheduledDate = scheduledDate;
        this.defaultWin = defaultWin;
    }

    public Match(Integer bestOf, Integer redTeamScore, Integer blueTeamScore, MatchStatus status, Date scheduledDate, Boolean defaultWin) {
        this.bestOf = bestOf;
        this.redTeamScore = redTeamScore;
        this.blueTeamScore = blueTeamScore;
        this.status = status;
        this.scheduledDate = scheduledDate;
        this.defaultWin = defaultWin;
    }
}
