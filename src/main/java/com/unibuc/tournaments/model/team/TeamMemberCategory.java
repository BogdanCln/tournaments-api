package com.unibuc.tournaments.model.team;

public class TeamMemberCategory {
    private int id;
    private int memberId;
    private String name;

    public TeamMemberCategory(int id, int memberId, String name) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
    }
}
