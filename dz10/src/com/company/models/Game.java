package com.company.models;

import com.company.enums.Genre;

public class Game {
    private final String name;
    private final Genre genre;
    private final MediumType type;

    private Game(String game, Genre genre, MediumType type){
        this.name = game;
        this.genre = genre;
        this.type = type;
    }

    public static GameDisk getDisk(String name, Genre genre, String description){
        return new GameDisk(name, genre, description);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre){
        return new VirtualGame(name, genre);
    }

    static class GameDisk{
        private final String description;
        private final Game data;
        private GameDisk(String name, Genre genre, String description) {
            this.description = description;
            this.data = new Game(name, genre, MediumType.PHYSICAL);
        }
        public String getDescription() {
            return description;
        }
        public Game getData() {
            return data;
        }
    }

    static class VirtualGame{
        private int rating;
        private final Game data;
        private VirtualGame(String name, Genre genre) {
            this.data = new Game(name, genre, MediumType.VIRTUAL);
        }
        public int getRating() {
            return rating;
        }
        public Game getData() {
            return data;
        }
        public void setRating(int rating) {
            if (rating >= 0 && rating <= 5){
                this.rating = rating;
            }
        }
    }

    enum MediumType{
        VIRTUAL, PHYSICAL
    }

    public String getName(){
        return name;
    }
    public Genre getGenre(){
        return genre;
    }
    public MediumType getType(){
        return type;
    }
}
