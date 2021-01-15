package com.unibuc.tournaments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibuc.tournaments.model.tournament.TournamentStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class TournamentUpdateRequest {
    @NotNull
    private Integer gameId;

    @NotBlank(message = "name not provided")
    private String name;

    // TODO: Custom validation / annotation for Enum
    // https://www.baeldung.com/javax-validations-enums
    private TournamentStatus status;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date endDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private String location;

    public TournamentUpdateRequest(@NotNull Integer gameId, @NotBlank(message = "name not provided") String name, TournamentStatus status, Date startDate, Date endDate, String location) {
        this.gameId = gameId;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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
