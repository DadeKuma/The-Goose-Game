package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.exception.PlayerNotFoundException;

import java.util.*;

public class Board {
    private int boardSize;
    private Collection<Player> players;
    private List<Integer> gooseSpaces;
    private Map<Integer, String> specialSpaceNames;
    private int bridgeSpace = 12;
    private boolean isGameFinished;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        players = new LinkedList<>();
        gooseSpaces = new LinkedList<>();
        specialSpaceNames = new HashMap<>();
        specialSpaceNames.put(0, "Start");
        specialSpaceNames.put(bridgeSpace, "The Bridge");
    }

    public Board addGooseSpaces(int...cells){
        for(Integer cell : cells){
            gooseSpaces.add(cell);
        }
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
        //then we check the bridge mechanic
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

        int oldBoardPosition = player.getPosition();
        int newBoardPosition = oldBoardPosition + diceRollsSum(diceRolls);
        player.setPosition(newBoardPosition);

        String oldSpace = spaceIntToString(oldBoardPosition);
        String newSpace = spaceIntToString(newBoardPosition);

        stringBuilder.append(playerName).append(" rolls ").append(diceRolls).append(". ");
        stringBuilder.append(playerName).append(" moves from ").append(oldSpace)
                     .append(" to ").append(newSpace);
        return stringBuilder;
    }

    private StringBuilder checkBridge(StringBuilder stringBuilder, Player player){
        String playerName = player.getName();

        if(player.getPosition() == bridgeSpace){
            player.setPosition(12);
            String newSpace = spaceIntToString(player.getPosition());
            stringBuilder.append(". ").append(playerName).append(" jumps to ").append(newSpace);
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
        String newSpace = spaceIntToString(player.getPosition());

        stringBuilder.append(", the Goose. ").append(playerName).append(" moves again ")
                     .append("and goes to ").append(newSpace);

        return checkGoose(stringBuilder, player, diceRolls);
    }

    private StringBuilder checkBouncing(StringBuilder stringBuilder, Player player){
        int playerPosition = player.getPosition();
        String playerName = player.getName();

        if(playerPosition > boardSize){
            int delta = playerPosition - boardSize;
            player.setPosition(boardSize - delta);
            String newSpace = spaceIntToString(player.getPosition());
            stringBuilder.append(". ").append(playerName).append( " bounces! ");
            stringBuilder.append(playerName).append( " returns to ").append(newSpace);
        }
        return stringBuilder;
    }

    private StringBuilder checkPrank(StringBuilder stringBuilder, Player player, int oldPosition){
        int playerPosition = player.getPosition();
        String playerName = player.getName();
        for(Player p : players){
            //there is a player p where player landed
            if(p != player && p.getPosition() == playerPosition){
                p.setPosition(oldPosition);
                String newSpace = spaceIntToString(player.getPosition());
                String prankSpace = spaceIntToString(p.getPosition());
                stringBuilder.append(". On ").append(newSpace).append(" there is ").append(p.getName())
                             .append(", who returns to ").append(prankSpace);
                break;
            }
        }
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

    private String spaceIntToString(int space){
        if(specialSpaceNames.containsKey(space))
            return specialSpaceNames.get(space);
        return String.valueOf(space);
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