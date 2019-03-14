package com.github.dadekuma.goosegame.game;

import java.util.Collection;
import java.util.LinkedList;

public class Board {
    private int boardSize;
    private Collection<Player> players;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        players = new LinkedList<>();
    }

    public String addPlayer(String name){
        Player newPlayer = new Player(name);
        if(players.contains(newPlayer)){
            return name + ": already existing player";
        }

        players.add(newPlayer);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("players: ");
        for(Player p : players){
            stringBuilder.append(p.getName());
            stringBuilder.append(", ");
        }
        //delete last ',' and ' '
        stringBuilder.delete(stringBuilder.length() - 2,
                            stringBuilder.length());
        return stringBuilder.toString();
    }

    public String movePlayer(String name, int amount){
        return "";
    }

    private boolean checkWin(String name){
        return false;
    }
}
