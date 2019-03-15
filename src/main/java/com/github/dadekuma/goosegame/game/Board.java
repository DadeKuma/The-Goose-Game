package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.exception.PlayerNotFoundException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private int boardSize;
    private Collection<Player> players;
    private List<Integer> gooseSpaces;
    private int bridgeSpace;
    private boolean isGameFinished;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        players = new LinkedList<>();
        gooseSpaces = new LinkedList<>();
    }

    public Board addGooseSpaces(int...cells){
        for(Integer cell : cells){
            gooseSpaces.add(cell);
        }
        return this;
    }

    public Board setBridgeSpace(int cell){
        bridgeSpace = cell;
        return this;
    }

    public boolean isGameFinished() { return isGameFinished;}

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
            throw new PlayerNotFoundException(name);
        StringBuilder stringBuilder = new StringBuilder();
        int oldPosition = player.getPosition();
        //we move the player.
        checkMovement(stringBuilder, player, diceRolls);
        //then we check the goose mechanic
        checkBridge(stringBuilder, player);
        //...the goose mechanic
        checkGoose(stringBuilder, player, diceRolls);
        //...the bouncing mechanic.
        checkBouncing(stringBuilder, player);
        //...the prank mechanic.
        checkPrank(stringBuilder, player, oldPosition);
        //and finally the win condition.
        checkWin(stringBuilder, player);
        return stringBuilder.toString();
    }

    private StringBuilder checkMovement(StringBuilder stringBuilder, Player player, String diceRolls){
        String playerName = player.getName();
        //position before throwing dices
        int oldPosition = player.getPosition();
        //sum of every dice result
        int diceResult = diceRollsSum(diceRolls);
        //position after throwing dices
        player.setPosition(oldPosition + diceResult);
        stringBuilder.append(playerName).append(" rolls ").append(diceRolls).append(". ");
        stringBuilder.append(playerName).append(" moves from ").append(oldPosition)
                     .append(" to ").append(player.getPosition());
        return stringBuilder;
    }

    private StringBuilder checkBridge(StringBuilder stringBuilder, Player player){
        String playerName = player.getName();
        int playerPosition = player.getPosition();
        if(playerPosition == bridgeSpace){
            player.setPosition(12);
            stringBuilder.append(". ").append(playerName).append(" jumps to ").append(player.getPosition());
        }
        return stringBuilder;
    }

    private StringBuilder checkGoose(StringBuilder stringBuilder, Player player, String diceRolls){
        int playerPosition = player.getPosition();
        //not a goose space so we get out
        if(!gooseSpaces.contains(playerPosition))
            return stringBuilder;

        String playerName = player.getName();
        int diceResult = diceRollsSum(diceRolls);
        player.setPosition(playerPosition + diceResult);

        stringBuilder.append(", the Goose. ").append(playerName).append(" moves again ")
                     .append("and goes to ").append(player.getPosition());

        return checkGoose(stringBuilder, player, diceRolls);
    }

    private StringBuilder checkBouncing(StringBuilder stringBuilder, Player player){
        int playerPosition = player.getPosition();
        String playerName = player.getName();

        if(playerPosition > boardSize){
            int delta = playerPosition - boardSize;
            player.setPosition(boardSize - delta);
            stringBuilder.append(". ").append(playerName).append( " bounces! ");
            stringBuilder.append(playerName).append( " returns to ").append(player.getPosition());
        }
        return stringBuilder;
    }

    private StringBuilder checkPrank(StringBuilder stringBuilder, Player player, int oldPosition){
        return stringBuilder;
    }

    private StringBuilder checkWin(StringBuilder stringBuilder, Player player){
        int playerPosition = player.getPosition();
        String playerName = player.getName();
        if(playerPosition == boardSize){
            stringBuilder.append(". ").append(playerName).append( " Wins!!");
            isGameFinished = true;
        }
        return stringBuilder;
    }

    //utility function used to get dice string values as a single int
    private int diceRollsSum(String diceRolls){
        String[] dices = diceRolls.split(", ");
        int result = 0;
        for(String dice : dices){
            result += Integer.valueOf(dice);
        }
        return result;
    }
}