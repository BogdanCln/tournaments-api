package com.unibuc.tournaments.dto;

import com.unibuc.tournaments.model.GameGenre;

import javax.validation.constraints.NotBlank;

public class GameRequest {
    private String publisherName;

    @NotBlank
    private String name;

    // TODO: Custom validation / annotation for Enum
    // https://www.baeldung.com/javax-validations-enums
    private GameGenre genre;

    public GameRequest() {
    }

    public GameRequest(String publisherName, String name, GameGenre genre) {
        this.publisherName = publisherName;
        this.name = name;
        this.genre = genre;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameGenre getGenre() {
        return genre;
    }

    public void setGenre(GameGenre genre) {
        this.genre = genre;
    }
}
