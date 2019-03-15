package com.github.dadekuma.goosegame.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardAddPlayerTest {

    private Board board;

    @Before
    public void Init(){
        board = new Board(64);
    }

    @Test
    public void addPlayer1() {
        String result = board.addPlayer("pippo");
        Assert.assertEquals("players: pippo", result);
    }

    @Test
    public void addPlayer2() {
        board.addPlayer("pippo");
        board.addPlayer("pluto");
        String result = board.addPlayer("paperino");

        Assert.assertEquals("players: pippo, pluto, paperino", result);
    }

    @Test
    public void addPlayerError1(){
        board.addPlayer("pippo");
        String result = board.addPlayer("pippo");

        Assert.assertEquals("pippo: already existing player", result);
    }

    @Test
    public void addPlayerError2(){
        board.addPlayer("pippo");
        board.addPlayer("pluto");
        String result = board.addPlayer("paperino");

        Assert.assertEquals("players: pippo, pluto, paperino", result);

        result = board.addPlayer("pippo");

        Assert.assertEquals("pippo: already existing player", result);
    }
}