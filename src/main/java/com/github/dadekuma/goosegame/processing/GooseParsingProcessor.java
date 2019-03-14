package com.github.dadekuma.goosegame.processing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooseParsingProcessor implements ParsingProcessor {
    //I think that the best way to handle this problem
    //is to just use regular expressions

    public GooseCommand parseInput(String input){
        //must start with 'add player'
        if(input.matches("^add player .*")){
            return addPlayer(input);
        }
        //in 'human' language: start with 'move' then any character; end with a digit.
        //then there can be 0-infinite times ', digit'.
        if(input.matches("^move .*&.* [0-9](, [0-9])*")){
            return movePlayer(input, true);
        }
        //same thing as above but only check for name
        if(input.matches("^move .*")){
            return movePlayer(input, true);
        }

        throw new CommandNotFoundException();
    }

    private GooseCommand addPlayer(String input){
        String playerName = input.substring(11);
        return new GooseCommand(EnumCommand.ADD_PLAYER, playerName);
    }

    private GooseCommand movePlayer(String input, boolean isRolling){
        //todo add player name to parameters
        if(!isRolling){
            return new GooseCommand(EnumCommand.MOVING);
        }
        //then we extract only the digits and finally add them together!
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        int amount = 0;
        while (matcher.find()){
            amount += Integer.valueOf(matcher.group());
        }
        return new GooseCommand(EnumCommand.MOVING_ROLL, amount);
    }
}
