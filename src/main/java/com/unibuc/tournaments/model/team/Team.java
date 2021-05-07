package com.unibuc.tournaments.model.team;

import lombok.Data;

import java.util.List;

@Data()
public class Team {
    private int id;
    private int gameId;
    private String name;
    private List<TeamMember> members;

    public Team(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }

    public Team(int id, int gameId, String name) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
    }
}
