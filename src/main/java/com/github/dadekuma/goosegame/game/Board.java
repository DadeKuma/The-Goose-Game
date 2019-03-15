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
        //player already exists
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
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    public String movePlayer(String name, String diceRolls){
        int oldPosition = 0, newPosition = 0;
        return name + " rolls " + diceRolls + "." +
                name + " moves from " + oldPosition + " to " + newPosition;
    }

    public boolean checkWinCondition() { return false;}

    private boolean checkWin(Player player){
        return player.getPosition() == boardSize;
    }
}
