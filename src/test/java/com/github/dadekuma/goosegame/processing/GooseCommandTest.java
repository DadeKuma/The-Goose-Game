package com.github.dadekuma.goosegame.processing;

import com.github.dadekuma.goosegame.processing.enums.EnumCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.ParameterNotFoundException;
import org.junit.Assert;
import org.junit.Test;


public class GooseCommandTest {

    @Test
    public void commandEqualityTest1() {
        GooseCommand firstCommand = new GooseCommand(EnumCommand.ADD_PLAYER, EnumParameter.PLY_NAME, "test");
        GooseCommand secondCommand = new GooseCommand(EnumCommand.ADD_PLAYER, EnumParameter.PLY_NAME, "test");

        Assert.assertEquals(firstCommand, secondCommand);
    }

    @Test
    public void commandEqualityTest2() {
        GooseCommand firstCommand = new GooseCommand(EnumCommand.MOVING);
        GooseCommand secondCommand = new GooseCommand(EnumCommand.MOVING);

        Assert.assertEquals(firstCommand, secondCommand);
    }

    @Test
    public void commandEqualityTest3() {
        GooseCommand firstCommand = new GooseCommand(EnumCommand.MOVING, EnumParameter.PLY_NAME,"pippo");
        String imNotACommand = "";

        Assert.assertNotEquals(firstCommand, imNotACommand);
    }

    @Test
    public void getParameterTest() {
        GooseCommand command = new GooseCommand(EnumCommand.MOVING, EnumParameter.PLY_NAME,"pippo");
        String result = command.getValue(EnumParameter.PLY_NAME);

        Assert.assertEquals("pippo", result);
    }

    @Test(expected = ParameterNotFoundException.class)
    public void parameterNotFound() {
        GooseCommand firstCommand = new GooseCommand(EnumCommand.MOVING, EnumParameter.PLY_NAME,"pippo");
        firstCommand.getValue(EnumParameter.ROLLS);
    }
}