package com.github.dadekuma.goosegame.processing.exception;

import com.github.dadekuma.goosegame.processing.enums.EnumCommand;

public class CommandNotFoundException extends RuntimeException {
    private String message;

    public CommandNotFoundException(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot find any command in: ")
                     .append("\"").append(input).append( "\".\n")
                     .append("Available commands are:\n");
        for(EnumCommand enumCommand : EnumCommand.values()){
           stringBuilder.append(enumCommand.toString()).append("\n");
        }
        message = stringBuilder.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
