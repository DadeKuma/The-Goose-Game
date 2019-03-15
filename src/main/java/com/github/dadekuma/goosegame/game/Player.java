package com.github.dadekuma.goosegame.game;

public class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Player))
            return false;
        return ((Player) obj).getName().equals(name);
    }
}
