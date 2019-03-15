package com.github.dadekuma.goosegame.game;

import org.junit.Assert;
import org.junit.Test;

public class DieTest {

    @Test
    public void rollDie() {
        Die firstDie = new Die(5, 1234);
        Die secondDie = new Die(3, 7652);

        boolean isLimitedToSides = true;
        for(int i = 0; i < 1000; ++i){
            int firstRoll = firstDie.roll();
            int secondRoll = secondDie.roll();
            isLimitedToSides &= firstRoll > 0 && firstRoll <= 5;
            isLimitedToSides &= secondRoll > 0 && secondRoll <= 3;
        }

        Assert.assertTrue(isLimitedToSides);
    }

    @Test
    public void rollRandomDie() {
        Die firstDie = new Die(5);

        boolean isLimitedToSides = true;
        for(int i = 0; i < 3000; ++i){
            int firstRoll = firstDie.roll();
            isLimitedToSides &= firstRoll > 0 && firstRoll <= 5;
        }

        Assert.assertTrue(isLimitedToSides);
    }
}