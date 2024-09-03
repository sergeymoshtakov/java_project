package com.company.models;

import com.company.enums.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PlayRoom {
    public static void main(String[] args) {
        Random rand = new Random();

        Game.GameDisk[] physicalGames = new Game.GameDisk[4];
        physicalGames[0] = Game.getDisk("Game 1", Genre.ACTION, "An action-packed adventure game.");
        physicalGames[1] = Game.getDisk("Game 2", Genre.RPG, "A role-playing game with deep storylines.");
        physicalGames[2] = Game.getDisk("Game 3", Genre.SPORT, "A thrilling sports game.");
        physicalGames[3] = Game.getDisk("Game 4", Genre.STRATEGY, "A strategy game that challenges your mind.");

        Game.VirtualGame[] virtualGames = new Game.VirtualGame[4];
        virtualGames[0] = Game.getVirtualGame("Game 5", Genre.ACTION);
        virtualGames[1] = Game.getVirtualGame("Game 6", Genre.RPG);
        virtualGames[2] = Game.getVirtualGame("Game 7", Genre.SPORT);
        virtualGames[3] = Game.getVirtualGame("Game 8", Genre.STRATEGY);

        for (int i = 0; i < virtualGames.length; i++) {
            virtualGames[i].setRating(rand.nextInt(6));
        }

        GameConsole console = new GameConsole(Producer.SONY, "SN12345678");

        Arrays.sort(physicalGames, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk a, Game.GameDisk b) {
                return a.getData().getGenre().compareTo(b.getData().getGenre());
            }
        });

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame a, Game.VirtualGame b) {
                return Integer.compare(a.getRating(), b.getRating());
            }
        });

        console.loadGame(physicalGames[0].getData());
        console.playGame();

        for (int i = 0; i < 6; i++) {
            console.getFirstGamePad().powerOff();
            console.getSecondGamePad().powerOff();
            console.playGame();
        }

        console.getFirstGamePad().powerOn();
        console.loadGame(virtualGames[0].getData());
        console.playGame();
    }
}
