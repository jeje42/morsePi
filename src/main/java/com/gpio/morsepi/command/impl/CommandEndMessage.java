package com.gpio.morsepi.command.impl;

import com.gpio.morsepi.command.Command;
import com.gpio.morsepi.gpio.GpioWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Scope("prototype")
public class CommandEndMessage implements Command {

    GpioWrapper gpioWrapper;

    public CommandEndMessage(GpioWrapper gpioWrapper) {
        this.gpioWrapper = gpioWrapper;
    }

    @Override
    public void execute() {
        IntStream.range(0, 2).forEach(i -> gpioWrapper.pauseWord());
    }
}
