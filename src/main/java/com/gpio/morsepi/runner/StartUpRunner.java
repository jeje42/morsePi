package com.gpio.morsepi.runner;

import com.gpio.morsepi.data.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StartUpRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartUpRunner.class);

    ApplicationContext context;

    @Getter
    Data data;

    CommandThread commandThread;

    public StartUpRunner(ApplicationContext context) {
        this.context = context;
    }

    public void setData(Data data) {
        this.data = data;

        startNewThread();
    }

    @Override
    public void run(String... args) {
        setData(Data.builder()
                .active(true)
                .text("Hello there")
                .build());
    }

    private void startNewThread() {
        stopCurrentThread();

        commandThread = context.getBean(CommandThread.class);
        commandThread.setData(data);
        commandThread.start();
    }

    private void stopCurrentThread() {
        try {
            commandThread.interrupt();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
