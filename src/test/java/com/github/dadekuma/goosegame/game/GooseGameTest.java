package com.github.dadekuma.goosegame.game;

import com.github.dadekuma.goosegame.processing.GooseCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GooseGameTest {
    @Mock
    private Die die;
    @InjectMocks
    private GooseGame mockedGooseGame;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gameCommandTest1(){
        mockedGooseGame = new GooseGame();
        GooseCommand command = new GooseCommand(EnumCommand.ADD_PLAYER,  EnumParameter.PLY_NAME, "Pippo");
        String result = mockedGooseGame.executeCommand(command);
        Assert.assertEquals("players: Pippo", result);
    }

    @Test
    public void gameCommandTest2(){
        GooseCommand command = new GooseCommand(EnumCommand.ADD_PLAYER,  EnumParameter.PLY_NAME, "Pippo");
        String result = mockedGooseGame.executeCommand(command);
        Assert.assertEquals("players: Pippo", result);
    }

    @Test
    public void gameCommandTest3(){
        GooseCommand command1 = new GooseCommand(EnumCommand.ADD_PLAYER,  EnumParameter.PLY_NAME, "Pippo");
        GooseCommand command2 = new GooseCommand(EnumCommand.MOVING_ROLL);
        command2.addParameter(EnumParameter.PLY_NAME, "Pippo")
                .addParameter(EnumParameter.ROLLS, "3, 4, 1");

        mockedGooseGame.executeCommand(command1);
        String result = mockedGooseGame.executeCommand(command2);
        Assert.assertEquals("Pippo rolls 3, 4, 1. Pippo moves from Start to 8", result);
    }

    @Test
    public void gameCommandTest4(){
        //simulate getting only 2s when rolling the die
        Mockito.when(die.roll())
                .thenReturn(2);
        GooseCommand command1 = new GooseCommand(EnumCommand.ADD_PLAYER,  EnumParameter.PLY_NAME, "Pippo");
        GooseCommand command2 = new GooseCommand(EnumCommand.MOVING, EnumParameter.PLY_NAME, "Pippo");

        mockedGooseGame.executeCommand(command1);
        String result = mockedGooseGame.executeCommand(command2);
        Assert.assertEquals("Pippo rolls 2, 2. Pippo moves from Start to 4", result);
    }
}