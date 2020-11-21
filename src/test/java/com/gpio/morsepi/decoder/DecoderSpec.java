package com.gpio.morsepi.decoder;

import com.gpio.morsepi.command.Command;
import com.gpio.morsepi.command.impl.*;
import com.gpio.morsepi.gpio.GpioWrapper;
import com.gpio.morsepi.runner.StartUpRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
class DecoderSpec {

    @Autowired
    Decoder decoder;

    @MockBean
    private GpioWrapper gpioWrapper;

    @MockBean
    private StartUpRunner startUpRunner;

    @Test
    public void decodeTest() {
        String text = "This is a message";

        List<Command> commands = decoder.decode(text);

        assertTrue(commands.get(0) instanceof CommandT);
        assertTrue(commands.get(1) instanceof CommandBetweenLetter);
        assertTrue(commands.get(2) instanceof CommandH);
        assertTrue(commands.get(3) instanceof CommandBetweenLetter);
        assertTrue(commands.get(4) instanceof CommandI);
        assertTrue(commands.get(5) instanceof CommandBetweenLetter);
        assertTrue(commands.get(6) instanceof CommandS);

        assertTrue(commands.get(7) instanceof CommandSpace);

        assertTrue(commands.get(8) instanceof CommandI);
        assertTrue(commands.get(9) instanceof CommandBetweenLetter);
        assertTrue(commands.get(10) instanceof CommandS);

        assertTrue(commands.get(11) instanceof CommandSpace);

        assertTrue(commands.get(12) instanceof CommandA);

        assertTrue(commands.get(13) instanceof CommandSpace);

        assertTrue(commands.get(14) instanceof CommandM);
        assertTrue(commands.get(15) instanceof CommandBetweenLetter);
        assertTrue(commands.get(16) instanceof CommandE);
        assertTrue(commands.get(17) instanceof CommandBetweenLetter);
        assertTrue(commands.get(18) instanceof CommandS);
        assertTrue(commands.get(19) instanceof CommandBetweenLetter);
        assertTrue(commands.get(20) instanceof CommandS);
        assertTrue(commands.get(21) instanceof CommandBetweenLetter);
        assertTrue(commands.get(22) instanceof CommandA);
        assertTrue(commands.get(23) instanceof CommandBetweenLetter);
        assertTrue(commands.get(24) instanceof CommandG);
        assertTrue(commands.get(25) instanceof CommandBetweenLetter);
        assertTrue(commands.get(26) instanceof CommandE);

    }
}
