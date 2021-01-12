package com.unibuc.tournaments.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeamRequest {
    @NotNull
    private int gameId;
    @NotBlank
    private String name;

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

    public TeamRequest(@NotBlank int gameId, @NotBlank String name) {
        this.gameId = gameId;
        this.name = name;
    }
}
