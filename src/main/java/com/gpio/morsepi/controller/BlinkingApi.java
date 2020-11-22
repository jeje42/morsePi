package com.gpio.morsepi.controller;

import com.gpio.morsepi.data.Data;
import com.gpio.morsepi.runner.StartUpRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blink")
public class BlinkingApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    StartUpRunner startUpRunner;

    public BlinkingApi(StartUpRunner startUpRunner) {
        this.startUpRunner = startUpRunner;
    }

    @GetMapping("/get")
    public Data getData() {
        return startUpRunner.getData();
    }

    @GetMapping("/set")
    public void setData(@RequestParam(required = false) Boolean active, @RequestParam(required = false) String text) {
        if (active == null && text == null) {
            logger.info("required active and text are null, skipping.");
        } else if (active != null && text == null) {
            if (active) {
                startUpRunner.setData(Data.builder()
                        .active(true)
                        .text(getData().getText())
                        .build());
            } else {
                startUpRunner.setData(Data.builder()
                        .active(false)
                        .text(getData().getText())
                        .build());
            }
        } else if (active == null) {
            startUpRunner.setData(Data.builder()
                    .active(true)
                    .text(text)
                    .build());
        } else {
            startUpRunner.setData(Data.builder()
                    .active(active)
                    .text(text)
                    .build());
        }
    }
}
