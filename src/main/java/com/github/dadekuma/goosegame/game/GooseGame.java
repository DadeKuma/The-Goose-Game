package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.*;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.CommandNotFoundException;
import com.github.dadekuma.goosegame.processing.exception.ParameterNotFoundException;
import com.github.dadekuma.goosegame.processing.exception.PlayerNotFoundException;

public class GooseGame {
    private Board board;
    private Die die;
    private int diceNumber;
    private InputOutputProcessor inputOutputProcessor;
    private ParsingProcessor parsingProcessor;

    public GooseGame() {
        inputOutputProcessor = new ConsoleInputOutputProcessor(System.in);
        parsingProcessor = new GooseParsingProcessor();
        board = new Board(63);
        board.addGooseSpaces(5, 9, 14, 18, 23, 27);
        board.addBridge(6);
        die = new Die(6);
        diceNumber = 2;
    }

    //called to start the game, contains the game loop
    public void start(){
        while (!board.isGameFinished()){
            update();
        }
        inputOutputProcessor.processOutput("Thank you for playing.");
        inputOutputProcessor.processStringInput();
    }

    protected void update(){
        try {
            String lastInput = inputOutputProcessor.processStringInput();
            GooseCommand command = parsingProcessor.parseInput(lastInput);
            String result = executeCommand(command);
            inputOutputProcessor.processOutput(result);
        } catch (PlayerNotFoundException |
                CommandNotFoundException |
                ParameterNotFoundException e){
            inputOutputProcessor.processOutput(e.getMessage());
        }
    }

    public String executeCommand(GooseCommand command){
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
                    int diceResult = die.roll();
                    stringBuilder.append(diceResult);
                    stringBuilder.append(", ");
                }
                String diceRolls = stringBuilder.substring(0, stringBuilder.length() - 2);
                return board.movePlayer(playerName, diceRolls);
            }
            //player tells explicitly his die result then moves
            case MOVING_ROLL: {
                String playerName = command.getValue(EnumParameter.PLY_NAME);
                String diceRolls = command.getValue(EnumParameter.ROLLS);
                return board.movePlayer(playerName, diceRolls);
            }
            default:
                throw new CommandNotFoundException(command.toString());
        }
    }
}
