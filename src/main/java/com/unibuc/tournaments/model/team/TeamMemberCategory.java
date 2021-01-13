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

    public TeamMemberCategory(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
