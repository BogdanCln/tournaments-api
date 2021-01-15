package com.unibuc.tournaments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibuc.tournaments.model.tournament.MatchStatus;

import java.util.Date;

public class MatchUpdateRequest {
    private Integer bestOf;
    private Integer redTeamScore;
    private Integer blueTeamScore;
    private MatchStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date scheduledDate;

    private Boolean defaultWin;

    public MatchUpdateRequest(Integer bestOf, Integer redTeamScore, Integer blueTeamScore, MatchStatus status, Date scheduledDate, Boolean defaultWin) {
        this.bestOf = bestOf;
        this.redTeamScore = redTeamScore;
        this.blueTeamScore = blueTeamScore;
        this.status = status;
        this.scheduledDate = scheduledDate;
        this.defaultWin = defaultWin;
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
}
