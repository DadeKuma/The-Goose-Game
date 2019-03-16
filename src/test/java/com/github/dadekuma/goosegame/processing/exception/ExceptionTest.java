package com.github.dadekuma.goosegame.processing.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void commandExceptionMessage(){
        String wrongCommand = "clearly not a command";
        String expectedMessage = "Cannot find any command in: \"" + wrongCommand + "\".\n" +
                "Available commands are:\n" +
                "add player <player_name>\n" +
                "move <player_name>\n" +
                "move <player_name> <die_value>(<, die_value>)\n";

        ex.expect(CommandNotFoundException.class);
        ex.expectMessage(expectedMessage);

        throw new CommandNotFoundException(wrongCommand);
    }

    @Test
    public void parameterExceptionMessage(){
        String notExistingParameter = "not a parameter";
        String expectedMessage = "Parameter name: \"" + notExistingParameter + "\" doesn't exist.";

        ex.expect(ParameterNotFoundException.class);
        ex.expectMessage(expectedMessage);

        throw new ParameterNotFoundException(notExistingParameter);
    }

    @Test
    public void playerExceptionMessage(){
        String notExistingPlayer = "not a player";
        String expectedMessage = "Player name: \"" + notExistingPlayer + "\" doesn't exist.";

        ex.expect(PlayerNotFoundException.class);
        ex.expectMessage(expectedMessage);

        throw new PlayerNotFoundException(notExistingPlayer);
    }
}