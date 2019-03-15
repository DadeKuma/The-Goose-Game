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

    public int roll(){
        return 1 + random.nextInt(sides);
    }
}
