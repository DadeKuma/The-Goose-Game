package com.github.dadekuma.goosegame.processing;

public enum EnumCommand {
    MOVING("move"),
    MOVING_ROLL("moveRoll"),
    ADD_PLAYER("add")
    ;

    private String name;
    EnumCommand(String name) {
        this.name = name;
    }
}
