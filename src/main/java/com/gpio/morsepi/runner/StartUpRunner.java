package com.gpio.morsepi.runner;

import com.gpio.morsepi.data.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StartUpRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ApplicationContext context;

    @Getter
    @Setter
    Data data = Data.builder()
            .active(true)
            .text("Hello there")
            .build();

    public StartUpRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        CommandThread commandThread;
        while (true) {
            commandThread = context.getBean(CommandThread.class);
            commandThread.setData(data);
            commandThread.start();

            commandThread.join();

            if (data == null || !data.isActive()) {
                Thread.sleep(10000);
            }
        }

    }
}
