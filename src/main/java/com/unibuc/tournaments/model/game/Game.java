package com.unibuc.tournaments.model.game;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
