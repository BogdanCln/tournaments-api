package com.unibuc.tournaments.dto;

import javax.validation.constraints.NotNull;

public class TeamMemberCategoryRequest {
    @NotNull
    private int memberId;
    @NotNull
    private String name;

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public TeamMemberCategoryRequest(@NotNull int memberId, @NotNull String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
