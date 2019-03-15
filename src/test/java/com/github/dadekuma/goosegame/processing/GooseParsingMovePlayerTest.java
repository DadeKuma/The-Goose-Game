package com.github.dadekuma.goosegame.processing;

import com.github.dadekuma.goosegame.processing.enums.EnumCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.CommandNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GooseParsingMovePlayerTest {
    private  GooseParsingProcessor gooseParsingProcessor;

    @Before
    public void Init(){
        gooseParsingProcessor = new GooseParsingProcessor();
    }

    @Test
    public void movePlayer1() {
        String input = "move pippo";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(
                EnumCommand.MOVING, EnumParameter.PLY_NAME,"pippo");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void movePlayer2() {
        String input = "move mickey mouse";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(
                EnumCommand.MOVING, EnumParameter.PLY_NAME,"mickey mouse");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void movePlayer3() {
        String input = "move mickey 20 mouse";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(
                EnumCommand.MOVING, EnumParameter.PLY_NAME,"mickey 20 mouse");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void moveRollPlayer1() {
        String input = "move mickey 20";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(EnumCommand.MOVING_ROLL);
        expectedCommand.addParameter(EnumParameter.PLY_NAME,"mickey")
                        .addParameter(EnumParameter.ROLLS, "20");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void moveRollPlayer2() {
        String input = "move mickey 20, 5, 7";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(EnumCommand.MOVING_ROLL);
        expectedCommand.addParameter(EnumParameter.PLY_NAME,"mickey")
                        .addParameter(EnumParameter.ROLLS, "20, 5, 7");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError1(){
        String input = "movepippo";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError2(){
        String input = " move pippo";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError3(){
        String input = "mo ve pippo";
        gooseParsingProcessor.parseInput(input);
    }
}