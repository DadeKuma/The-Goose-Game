package com.github.dadekuma.goosegame.game;

import java.util.Random;

public class Dice {
    private Random random;
    private int sides;

    public Dice(int sides) {
        this(sides, new Random().nextInt());
    }

    public Dice(int sides, int seed) {
        this.sides = sides;
        random = new Random(seed);
    }

    //dices don't have a 'zero' value :)
    public int roll(){
        return 1 + random.nextInt(sides);
    }
}
