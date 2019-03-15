package com.github.dadekuma.goosegame.processing.exception;

public class ParameterNotFoundException extends RuntimeException {
    private String message;

    public ParameterNotFoundException(String parameterName) {
        message = "Parameter name: \"" + parameterName + "\" doesn't exist.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
