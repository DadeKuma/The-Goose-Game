package com.github.dadekuma.goosegame.processing;

import java.util.Scanner;

public class ConsoleInputOutputProcessor implements InputOutputProcessor {
    //without an interface it would be difficult to refactor the game
    //from a console based application to say, a real game with graphics :)
    private Scanner scanner;

    public ConsoleInputOutputProcessor() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String processStringInput() {
        return scanner.nextLine();
    }

    @Override
    public int processIntInput() {
        return scanner.nextInt();
    }

    @Override
    public void processOutput(String output) {
        System.out.println(output);
    }
}
