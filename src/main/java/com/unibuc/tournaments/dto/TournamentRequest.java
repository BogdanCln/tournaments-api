package com.unibuc.tournaments.dto;

import com.unibuc.tournaments.model.tournament.TournamentStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class TournamentRequest {
    @NotNull
    private int gameId;

    @NotBlank(message = "name not provided")
    private String name;

    // TODO: Custom validation / annotation for Enum
    // https://www.baeldung.com/javax-validations-enums
    private TournamentStatus status;

    @Size(min = 2, max = 4096)
    private List<Integer> teams;

    private Date startDate;
    private Date endDate;
    private String location;

    public TournamentRequest(@NotNull int gameId, @NotBlank(message = "name not provided") String name, TournamentStatus status, @Size(min = 2) List<Integer> teams, Date startDate, Date endDate, String location) {
        this.gameId = gameId;
        this.name = name;
        this.status = status;
        this.teams = teams;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus status) {
        this.status = status;
    }

    public List<Integer> getTeams() {
        return teams;
    }

    public void setTeams(List<Integer> teams) {
        this.teams = teams;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
