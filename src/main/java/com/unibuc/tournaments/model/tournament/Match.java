package com.unibuc.tournaments.model.tournament;

import java.util.Date;
import java.util.Dictionary;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBracketId() {
        return bracketId;
    }

    public void setBracketId(Integer bracketId) {
        this.bracketId = bracketId;
    }

    public String getBracketPhase() {
        return bracketPhase;
    }

    public void setBracketPhase(String bracketPhase) {
        this.bracketPhase = bracketPhase;
    }

    public Integer getRedTeamId() {
        return redTeamId;
    }

    public void setRedTeamId(Integer redTeamId) {
        this.redTeamId = redTeamId;
    }

    public Integer getBlueTeamId() {
        return blueTeamId;
    }

    public void setBlueTeamId(Integer blueTeamId) {
        this.blueTeamId = blueTeamId;
    }

    public Integer getBestOf() {
        return bestOf;
    }

    public void setBestOf(Integer bestOf) {
        this.bestOf = bestOf;
    }

    public Integer getRedTeamScore() {
        return redTeamScore;
    }

    public void setRedTeamScore(Integer redTeamScore) {
        this.redTeamScore = redTeamScore;
    }

    public Integer getBlueTeamScore() {
        return blueTeamScore;
    }

    public void setBlueTeamScore(Integer blueTeamScore) {
        this.blueTeamScore = blueTeamScore;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Boolean getDefaultWin() {
        return defaultWin;
    }

    public void setDefaultWin(Boolean defaultWin) {
        this.defaultWin = defaultWin;
    }

    public Dictionary<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Dictionary<String, String> customFields) {
        this.customFields = customFields;
    }
}
