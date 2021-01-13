package com.unibuc.tournaments.model.team;

import java.util.List;

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

    public int getId() {
        return id;
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

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }
}
