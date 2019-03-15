package com.github.dadekuma.goosegame.processing.enums;

public enum EnumParameter {
    PLY_NAME("playerName"),
    ROLLS("diceRolls"),
    ;

    private String name;
    EnumParameter(String name) {
        this.name = name;
    }
}
