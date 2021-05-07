package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.team.Team;
import lombok.Data;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data()
public class Tournament {
    private int id;
    private int gameId;
    private String name;
    private TournamentStatus status;
    private Date startDate;
    private Date endDate;
    private String location;
    private List<Integer> teams;

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
}
