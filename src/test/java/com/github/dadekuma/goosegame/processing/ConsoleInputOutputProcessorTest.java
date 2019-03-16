package com.github.dadekuma.goosegame.processing;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ConsoleInputOutputProcessorTest {

    @Test
    public void consoleTest1() {
        ByteArrayInputStream iStream = new ByteArrayInputStream("1".getBytes());
        ConsoleInputOutputProcessor consoleInputOutputProcessor = new ConsoleInputOutputProcessor(iStream);
        int result = consoleInputOutputProcessor.processIntInput();
        Assert.assertEquals(1, result);
    }

    @Test
    public void consoleTest2() {
        ByteArrayInputStream iStream = new ByteArrayInputStream("test".getBytes());
        ConsoleInputOutputProcessor consoleInputOutputProcessor = new ConsoleInputOutputProcessor(iStream);
        String result = consoleInputOutputProcessor.processStringInput();
        Assert.assertEquals("test", result);
    }

    @Test
    public void consoleTest3() {
        ByteArrayInputStream iStream = new ByteArrayInputStream("test 123".getBytes());
        ConsoleInputOutputProcessor consoleInputOutputProcessor = new ConsoleInputOutputProcessor(iStream);
        String result = consoleInputOutputProcessor.processStringInput();
        Assert.assertEquals("test 123", result);
    }

    @Test
    public void consoleTest4() {
        ByteArrayInputStream iStream = new ByteArrayInputStream("".getBytes());
        ConsoleInputOutputProcessor consoleInputOutputProcessor = new ConsoleInputOutputProcessor(iStream);
        consoleInputOutputProcessor.processOutput("");
    }
}