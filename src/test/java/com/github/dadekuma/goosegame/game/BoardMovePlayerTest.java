package com.github.dadekuma.goosegame.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardMovePlayerTest {

    private Board board;

    @Before
    public void Init(){
        board = new Board(63);
    }

    @Test
    public void movePlayer1() {
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "6, 2");
        String expected = "pippo rolls 6, 2. pippo moves from Start to 8";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayer2() {
        board.addPlayer("pippo");
        board.movePlayer("pippo", "6, 2");

        String result = board.movePlayer("pippo", "10, 10, 10");
        String expected = "pippo rolls 10, 10, 10. pippo moves from 8 to 38";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerBridge() {
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "6");
        String expected = "pippo rolls 6. pippo moves from Start to The Bridge. pippo jumps to 12";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerGoose1(){
        board.addGooseSpaces(10);
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "10");
        String expected = "pippo rolls 10. pippo moves from Start to 10, the Goose. pippo moves again and goes to 20";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerGoose2(){
        board.addGooseSpaces(10);
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "3, 3, 3, 1");
        String expected = "pippo rolls 3, 3, 3, 1. pippo moves from Start to 10, the Goose. pippo moves again and goes to 20";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerGoose3(){
        board.addGooseSpaces(10, 20);
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "10");
        String expected = "pippo rolls 10. pippo moves from Start to 10, the Goose. "
        + "pippo moves again and goes to 20, the Goose. pippo moves again and goes to 30";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerGoose4(){
        board.addGooseSpaces(10, 20, 30);
        board.addPlayer("pippo");

        String result = board.movePlayer("pippo", "10");
        String expected = "pippo rolls 10. pippo moves from Start to 10, the Goose. "
                + "pippo moves again and goes to 20, the Goose. "
                + "pippo moves again and goes to 30, the Goose. pippo moves again and goes to 40";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerBounce(){
        board.addPlayer("pippo");

        board.movePlayer("pippo", "60");
        String result = board.movePlayer("pippo", "3, 5");
        String expected = "pippo rolls 3, 5. pippo moves from 60 to 63. pippo bounces! pippo returns to 58";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void movePlayerPrank(){
        board.addPlayer("pippo");
        board.addPlayer("pluto");

        board.movePlayer("pippo", "10");
        String result = board.movePlayer("pluto", "10");
        String expected = "pluto rolls 10. pluto moves from Start to 10. On 10 there is pippo, who returns to Start";

        Assert.assertEquals(expected, result);
    }
}