package com.gpio.morsepi.runner;

import com.gpio.morsepi.command.Command;
import com.gpio.morsepi.data.Data;
import com.gpio.morsepi.decoder.Decoder;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class CommandThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(StartUpRunner.class);

    Decoder decoder;

    @Setter
    Data data;

    public CommandThread(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void run() {
        if (data == null) {
            logger.error("No data available.");
            return;
        }

        if (data.isActive()) {
            logger.info("Started CommandRunner !");
            List<Command> commands = decoder.decode(data.getText());

            commands.forEach(Command::execute);

        }
    }
}
