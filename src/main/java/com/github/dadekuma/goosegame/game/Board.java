package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.exception.PlayerNotFoundException;

import java.util.Collection;
import java.util.LinkedList;

public class Board {
    private int boardSize;
    private Collection<Player> players;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        players = new LinkedList<>();
    }

    public boolean isGameFinished() { return false;}

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
        Player player = null;
        //get the player from the collection
        for(Player p : players){
            if(p.getName().equals(name)){
                player = p;
                break;
            }
        }
        if(player == null)
            throw new PlayerNotFoundException();

        //position before throwing dices
        int oldPosition = player.getPosition();
        //sum of every dice result
        int diceResult = diceRollsSum(diceRolls);
        //position after throwing dices
        player.setPosition(oldPosition + diceResult);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append(" rolls ").append(diceRolls).append(". ");
        stringBuilder.append(name).append(" moves from ")
                .append(oldPosition).append(" to ").append(player.getPosition()).append(". ");

        //here we have the bouncing mechanic.
        checkBouncing(stringBuilder, player);
        //and finally the win condition.
        checkWin(stringBuilder, player);
        return stringBuilder.toString();
    }

    private StringBuilder checkBouncing(StringBuilder stringBuilder, Player player){
        int playerPosition = player.getPosition();
        String playerName = player.getName();

        if(playerPosition > boardSize){
            int delta = playerPosition - boardSize;
            player.setPosition(boardSize - delta);
            stringBuilder.append(playerName).append( " bounces! ");
            stringBuilder.append(playerName).append( " returns to ").append(player.getPosition());
        }
        return stringBuilder;
    }

    private StringBuilder checkWin(StringBuilder stringBuilder, Player player){
        int playerPosition = player.getPosition();
        String playerName = player.getName();
        if(playerPosition == boardSize){
            stringBuilder.append(playerName).append( " Wins!!");
        }
        return stringBuilder;
    }

    private int diceRollsSum(String diceRolls){
        String[] dices = diceRolls.split(", ");
        int result = 0;
        for(String dice : dices){
            result += Integer.valueOf(dice);
        }
        return result;
    }
}
