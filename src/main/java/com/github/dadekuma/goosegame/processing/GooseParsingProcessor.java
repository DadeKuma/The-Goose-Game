package com.github.dadekuma.goosegame.processing;

import com.github.dadekuma.goosegame.processing.enums.EnumCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.CommandNotFoundException;

import java.util.HashMap;
import java.util.Map;
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
            return movePlayer(input, false);
        }
        throw new CommandNotFoundException();
    }

    private GooseCommand addPlayer(String input){
        String playerName = input.substring(11);
        return new GooseCommand(EnumCommand.ADD_PLAYER, EnumParameter.PLY_NAME, playerName);
    }

    private GooseCommand movePlayer(String input, boolean isRolling){
        Map<EnumParameter, String> parameters = new HashMap<>();
        String playerName = input.substring(11);
        parameters.put(EnumParameter.PLY_NAME, playerName);

        if(!isRolling){
            //if player didn't explicitly told dice results we stop here
            return new GooseCommand(EnumCommand.MOVING, parameters);
        }

        //if he did then we extract only the digits from input and finally add them together!
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()){
            stringBuilder.append(matcher.group());
            stringBuilder.append(", ");
        }
        String diceRolls = stringBuilder.substring(0, stringBuilder.length() - 2);
        parameters.put(EnumParameter.ROLLS, diceRolls);
        return new GooseCommand(EnumCommand.MOVING_ROLL, parameters);
    }
}
