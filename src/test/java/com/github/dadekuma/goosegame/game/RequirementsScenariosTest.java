package com.github.dadekuma.goosegame.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequirementsScenariosTest {

    private Board board;

    @Before
    public void Init(){
        board = new Board(63);
    }

    @Test
    public void addPlayerScenario() {
        String expected, result;

        result = board.addPlayer("Pippo");
        expected = "players: Pippo";
        Assert.assertEquals(expected, result);

        result = board.addPlayer("Pluto");
        expected = "players: Pippo, Pluto";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void duplicatedPlayerScenario() {
        board.addPlayer("Pippo");
        String result = board.addPlayer("Pippo");

        Assert.assertEquals("Pippo: already existing player", result);
    }

    @Test
    public void moveScenario() {
        String expected, result;
        board.addPlayer("Pippo");
        board.addPlayer("Pluto");

        result = board.movePlayer("Pippo", "4, 2");
        expected = "Pippo rolls 4, 2. Pippo moves from Start to 6";
        Assert.assertEquals(expected, result);

        result = board.movePlayer("Pluto", "2, 2");
        expected = "Pluto rolls 2, 2. Pluto moves from Start to 4";
        Assert.assertEquals(expected, result);

        result = board.movePlayer("Pippo", "2, 3");
        expected = "Pippo rolls 2, 3. Pippo moves from 6 to 11";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void victoryScenario() {
        String result;
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "60");
        result = board.movePlayer("Pippo", "1, 2");


        Assert.assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!", result);
    }

    @Test
    public void exactShootScenario() {
        String result;
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "60");
        result = board.movePlayer("Pippo", "3, 2");


        Assert.assertEquals("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61", result);
    }

    @Test
    public void diceRollScenario() {
        String result;
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "4");
        result = board.movePlayer("Pippo", "1, 2");


        Assert.assertEquals("Pippo rolls 1, 2. Pippo moves from 4 to 7", result);
    }

    @Test
    public void bridgeScenario() {
        String result;
        board.addBridge(6);
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "4");
        result = board.movePlayer("Pippo", "1, 1");

        Assert.assertEquals("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12", result);
    }

    @Test
    public void singleJumpScenario() {
        String result;
        board.addGooseSpaces(5, 9, 14, 18, 23, 27);
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "3");
        result = board.movePlayer("Pippo", "1, 1");

        String expected = "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void multipleJumpScenario() {
        String result;
        board.addGooseSpaces(5, 9, 14, 18, 23, 27);
        board.addPlayer("Pippo");
        board.movePlayer("Pippo", "10");
        result = board.movePlayer("Pippo", "2, 2");

        String expected = "Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose."+
                " Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void prankScenario() {
        String result;
        board.addPlayer("Pippo");
        board.addPlayer("Pluto");
        board.movePlayer("Pippo", "15");
        board.movePlayer("Pluto", "17");
        result = board.movePlayer("Pippo", "1, 1");

        String expected = "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15";

        Assert.assertEquals(expected, result);
    }
}