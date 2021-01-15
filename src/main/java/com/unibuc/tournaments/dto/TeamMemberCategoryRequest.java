package com.unibuc.tournaments.dto;

import javax.validation.constraints.NotNull;

public class TeamMemberCategoryRequest {
    @NotNull
    private Integer memberId;
    @NotNull
    private String name;

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public TeamMemberCategoryRequest(@NotNull Integer memberId, @NotNull String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
