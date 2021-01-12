package com.unibuc.tournaments.model.team;

public class TeamMemberCategory {
    private TeamMemberType memberType;
    private String name;

    public TeamMemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(TeamMemberType memberType) {
        this.memberType = memberType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamMemberCategory(TeamMemberType memberType, String name) {
        this.memberType = memberType;
        this.name = name;
    }
}
