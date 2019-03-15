package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.*;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.CommandNotFoundException;

public class GooseGame {
    private Board board;
    private Dice dice;
    private int diceNumber;
    private InputOutputProcessor InputOutputProcessor;
    private ParsingProcessor parsingProcessor;
    private boolean isGameFinished;

    public GooseGame() {
        InputOutputProcessor = new ConsoleInputOutputProcessor();
        parsingProcessor = new GooseParsingProcessor();
        board = new Board(63);
        dice = new Dice(6);
        diceNumber = 2;
    }

    //called to start the game, contains the game loop
    public void start(){
        while (!isGameFinished){
            String lastInput = InputOutputProcessor.processStringInput();
            GooseCommand command = parsingProcessor.parseInput(lastInput);
            String result = executeCommand(command);
            InputOutputProcessor.processOutput(result);
        }
    }

    private String executeCommand(GooseCommand command){
        switch (command.getName()){
            //add a player to the board
            case ADD_PLAYER: {
                String playerName = command.getValue(EnumParameter.PLY_NAME);
                return board.addPlayer(playerName);
            }
            //system rolls dices for player, then player moves
            case MOVING: {
                String playerName = command.getValue(EnumParameter.PLY_NAME);
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < diceNumber; ++i){
                    int diceResult = dice.roll();
                    stringBuilder.append(diceResult);
                    stringBuilder.append(", ");
                }
                String diceRolls = stringBuilder.substring(0, stringBuilder.length() - 2);
                return board.movePlayer(playerName, diceRolls);
            }
            //player tells explicitly his dice result then moves
            case MOVING_ROLL: {
                String playerName = command.getValue(EnumParameter.PLY_NAME);
                String diceRolls = command.getValue(EnumParameter.ROLLS);
                return board.movePlayer(playerName, diceRolls);
            }
            default:
                throw new CommandNotFoundException();
        }
    }
}