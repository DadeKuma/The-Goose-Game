package com.github.dadekuma.goosegame.processing.enums;

public enum EnumCommand {
    MOVING("move"),
    MOVING_ROLL("moveRoll"),
    ADD_PLAYER("addPlayer")
    ;

    private String name;
    EnumCommand(String name) {
        this.name = name;
    }
}
