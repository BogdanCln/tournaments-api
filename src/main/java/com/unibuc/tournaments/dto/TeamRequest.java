package com.unibuc.tournaments.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeamRequest {
    @NotNull
    private Integer gameId;
    @NotBlank
    private String name;

    public TeamRequest(@NotNull Integer gameId, @NotBlank String name) {
        this.gameId = gameId;
        this.name = name;
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
}
