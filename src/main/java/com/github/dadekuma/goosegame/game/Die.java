package com.github.dadekuma.goosegame.game;

import java.util.Random;

public class Die {
    private Random random;
    private int sides;

    public Die(int sides) {
        this(sides, new Random().nextInt());
    }

    public Die(int sides, int seed) {
        this.sides = sides;
        random = new Random(seed);
    }

    public int roll(){
        return 1 + random.nextInt(sides);
    }
}
