package com.unibuc.tournaments.model.game;

import lombok.Data;

import java.util.Objects;

@Data()
public class Game {
    private int id;
    private String name;
    private String publisherName;
    private GameGenre genre;

    public Game(int id, String name, String publisherName, GameGenre genre) {
        this.id = id;
        this.name = name;
        this.publisherName = publisherName;
        this.genre = Objects.requireNonNullElse(genre, GameGenre.NONE);
    }

    public Game(String name, String publisherName, GameGenre genre) {
        this.name = name;
        this.publisherName = publisherName;
        this.genre = genre;
        this.genre = Objects.requireNonNullElse(genre, GameGenre.NONE);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", genre=" + genre +
                '}';
    }
}
