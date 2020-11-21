package com.gpio.morsepi.command.impl;

import com.gpio.morsepi.command.Command;
import com.gpio.morsepi.gpio.GpioWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Command0 implements Command {

    GpioWrapper gpioWrapper;

    public Command0(GpioWrapper gpioWrapper) {
        this.gpioWrapper = gpioWrapper;
    }

    @Override
    public void execute() {
        gpioWrapper.longSign();
        gpioWrapper.pauseInLetter();
        gpioWrapper.longSign();
        gpioWrapper.pauseInLetter();
        gpioWrapper.longSign();
        gpioWrapper.pauseInLetter();
        gpioWrapper.longSign();
        gpioWrapper.pauseInLetter();
        gpioWrapper.longSign();
    }
}
