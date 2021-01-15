package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.team.Team;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tournament {
    private int id;
    private int gameId;
    private String name;
    private TournamentStatus status;
    private Date startDate;
    private Date endDate;
    private String location;
    private List<Integer> teams;
    private Dictionary<String, String> customFields;

    public Tournament(int gameId, String name, TournamentStatus status, Date startDate, Date endDate, String location, List<Integer> teams) {
        this.gameId = gameId;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.teams = teams;
    }

    public Tournament(int id, int gameId, String name, TournamentStatus status, Date startDate, Date endDate, String location) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public Tournament(int id, int gameId, String name, TournamentStatus status, Date startDate, Date endDate, String location, String teams) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        if (teams != null) {
            List<String> stringList = Arrays.asList(teams.split("\\s*,\\s*"));
            this.teams = stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
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

    public List<Integer> getTeams() {
        return teams;
    }

    public void setTeams(List<Integer> teams) {
        this.teams = teams;
    }

    public Dictionary<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Dictionary<String, String> customFields) {
        this.customFields = customFields;
    }
}
