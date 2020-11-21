package com.gpio.morsepi.decoder;

import com.gpio.morsepi.command.Command;
import com.gpio.morsepi.command.impl.*;
import com.gpio.morsepi.gpio.GpioWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
public class Decoder {

    GpioWrapper gpioWrapper;

    public Decoder(GpioWrapper gpioWrapper) {
        this.gpioWrapper = gpioWrapper;
    }

    public List<Command> decode(String text) {
        char[] textCharArray = text.toLowerCase().toCharArray();
        List<Command> commands = IntStream.range(0, textCharArray.length).mapToObj(i -> textCharArray[i]).map(c -> {
            switch (c) {
                case 'a':
                    return new CommandA(gpioWrapper);
                case 'b':
                    return new CommandB(gpioWrapper);
                case 'c':
                    return new CommandC(gpioWrapper);
                case 'd':
                    return new CommandD(gpioWrapper);
                case 'e':
                    return new CommandE(gpioWrapper);
                case 'f':
                    return new CommandF(gpioWrapper);
                case 'g':
                    return new CommandG(gpioWrapper);
                case 'h':
                    return new CommandH(gpioWrapper);
                case 'i':
                    return new CommandI(gpioWrapper);
                case 'j':
                    return new CommandJ(gpioWrapper);
                case 'k':
                    return new CommandK(gpioWrapper);
                case 'l':
                    return new CommandL(gpioWrapper);
                case 'm':
                    return new CommandM(gpioWrapper);
                case 'n':
                    return new CommandN(gpioWrapper);
                case 'o':
                    return new CommandO(gpioWrapper);
                case 'p':
                    return new CommandP(gpioWrapper);
                case 'q':
                    return new CommandQ(gpioWrapper);
                case 'r':
                    return new CommandR(gpioWrapper);
                case 's':
                    return new CommandS(gpioWrapper);
                case 't':
                    return new CommandT(gpioWrapper);
                case 'u':
                    return new CommandU(gpioWrapper);
                case 'v':
                    return new CommandV(gpioWrapper);
                case 'w':
                    return new CommandW(gpioWrapper);
                case 'x':
                    return new CommandX(gpioWrapper);
                case 'y':
                    return new CommandY(gpioWrapper);
                case 'z':
                    return new CommandZ(gpioWrapper);
                case '0':
                    return new Command0(gpioWrapper);
                case '1':
                    return new Command1(gpioWrapper);
                case '2':
                    return new Command2(gpioWrapper);
                case '3':
                    return new Command3(gpioWrapper);
                case '4':
                    return new Command4(gpioWrapper);
                case '5':
                    return new Command5(gpioWrapper);
                case '6':
                    return new Command6(gpioWrapper);
                case '7':
                    return new Command7(gpioWrapper);
                case '8':
                    return new Command8(gpioWrapper);
                case '9':
                    return new Command9(gpioWrapper);
                case ' ':
                    return new CommandSpace(gpioWrapper);
                default:
                    return new CommandNothing(gpioWrapper);
            }
        }).collect(toList());


        List<Command> toReturn = new ArrayList<>();

        IntStream.range(0, commands.size())
                .forEach(i -> {
                    Command c = commands.get(i);

                    toReturn.add(c);

                    if (!(c instanceof CommandSpace) && i < commands.size() - 1) {
                        Command nextCommand = commands.get(i + 1);
                        if (!(nextCommand instanceof CommandSpace)) {
                            toReturn.add(new CommandBetweenLetter(gpioWrapper));
                        }
                    }
                });

        return toReturn;
    }
}
