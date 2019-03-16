package com.github.dadekuma.goosegame.game;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void playerEqualityTest1() {
        Player playerOne = new Player("Pippo");
        String pippo = "pippo";

        Assert.assertNotEquals(playerOne, pippo);
    }

    @Test
    public void playerEqualityTest2() {
        Player playerOne = new Player("Pippo");
        Player playerTwo = new Player("Pippo");

        Assert.assertEquals(playerOne, playerTwo);
    }
}