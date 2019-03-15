package com.github.dadekuma.goosegame.processing.enums;

public enum EnumCommand {
    ADD_PLAYER("add player <player_name>"),
    MOVING("move <player_name>"),
    MOVING_ROLL("move <player_name> <dice>(<, dice>)"),
    ;

    private String name;
    EnumCommand(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
