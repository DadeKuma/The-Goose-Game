package com.github.dadekuma.goosegame.processing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GooseParsingProcessorTest {
    private  GooseParsingProcessor gooseParsingProcessor;

    @Before
    public void Init(){
        gooseParsingProcessor = new GooseParsingProcessor();
    }

    @Test
    public void addPlayer1() {
        String input = "add player pippo";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(EnumCommand.ADD_PLAYER, "pippo");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void addPlayer2() {
        String input = "add player mickey mouse";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(EnumCommand.ADD_PLAYER, "mickey mouse");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void addPlayer3() {
        String input = "add player pluto273";
        GooseCommand actualCommand = gooseParsingProcessor.parseInput(input);

        GooseCommand expectedCommand = new GooseCommand(EnumCommand.ADD_PLAYER, "pluto273");

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError1(){
        String input = "add player";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError2(){
        String input = "ad d player pippo";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError3(){
        String input = "player add pippo";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError4(){
        String input = "add player";
        gooseParsingProcessor.parseInput(input);
    }

    @Test(expected = CommandNotFoundException.class)
    public void addPlayerError5(){
        String input = "add playerpippo";
        gooseParsingProcessor.parseInput(input);
    }
}