package com.unibuc.tournaments.model.tournament;

import java.util.Date;
import java.util.Dictionary;

public class Match {
    private int id;
    private int bracketId;
    private String bracketPhase;
    private int redTeamId;
    private int blueTeamId;
    private int bestOf;
    private int redTeamScore;
    private int blueTeamScore;
    private MatchStatus status;
    private Date scheduledDate;
    private Boolean defaultWin;
    private Dictionary<String, String> customFields;

    public Match(int id, int bracketId, String bracketPhase, int redTeamId, int blueTeamId, int bestOf, int redTeamScore, int blueTeamScore, MatchStatus status, Date scheduledDate, Boolean defaultWin) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBracketId() {
        return bracketId;
    }

    public void setBracketId(int bracketId) {
        this.bracketId = bracketId;
    }

    public String getBracketPhase() {
        return bracketPhase;
    }

    public void setBracketPhase(String bracketPhase) {
        this.bracketPhase = bracketPhase;
    }

    public int getRedTeamId() {
        return redTeamId;
    }

    public void setRedTeamId(int redTeamId) {
        this.redTeamId = redTeamId;
    }

    public int getBlueTeamId() {
        return blueTeamId;
    }

    public void setBlueTeamId(int blueTeamId) {
        this.blueTeamId = blueTeamId;
    }

    public int getBestOf() {
        return bestOf;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public void setRedTeamScore(int redTeamScore) {
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public void setBlueTeamScore(int blueTeamScore) {
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
