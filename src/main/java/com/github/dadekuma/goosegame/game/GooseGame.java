package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.*;

public class GooseGame {
    private Board board;
    private Dice dice;
    private InputProcessor inputProcessor;
    private ParsingProcessor parsingProcessor;
    private boolean isGameFinished;

    public GooseGame() {
        inputProcessor = new ConsoleInputProcessor();
        parsingProcessor = new GooseParsingProcessor();
        board = new Board(63);
        dice = new Dice(6);
    }

    public void start(){
        while (!isGameFinished){
            String lastInput = inputProcessor.processStringInput();
            GooseCommand command = parsingProcessor.parseInput(lastInput);
            String result = executeCommand(command);
            inputProcessor.processOutput(result);
        }
    }

    private String executeCommand(GooseCommand command){
        switch (command.getName()){
            case ADD_PLAYER:
                String playerName = (String) command.getParameter();
                return board.addPlayer(playerName);
            case MOVING:
                //command.getParameter();
                //dice.roll();
                //return board.movePlayer();
            case MOVING_ROLL:
                //return board.movePlayer();
            default:
                throw new CommandNotFoundException();
        }
    }
}
