package com.github.dadekuma.goosegame.processing.exception;

public class PlayerNotFoundException extends RuntimeException {
    private String message;

    public PlayerNotFoundException(String playerName) {
        message = "Player name: \"" + playerName + "\" doesn't exist.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
