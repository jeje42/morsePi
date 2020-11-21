package com.gpio.morsepi.gpio;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class GpioWrapper {

    Logger logger = LoggerFactory.getLogger(GpioWrapper.class);

    String pinName;

    Double shortInterval;

    Double longInterval;

    Double pauseInterval;

    GpioPinDigitalOutput pin;

    GpioController gpio;

    public GpioWrapper(@Value("${gpio.pinName}") String pinName,
                       @Value("${morse.short}") Double shortInterval,
                       @Value("${morse.long}") Double longInterval,
                       @Value("${morse.pause}") Double pauseInterval) {
        logger.info("pinName: " + pinName);

        this.pinName = pinName;
        this.shortInterval = shortInterval;
        this.longInterval = longInterval;
        this.pauseInterval = pauseInterval;

        gpio = GpioFactory.getInstance();
        pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(pinName), "MyLED", PinState.LOW);
    }

    @PreDestroy
    public void onDestroy() {
        logger.error("Cleaning the gpio state.");
        pin.low();
        gpio.shutdown();
    }

    public void shortSign() {
        try {
            pin.pulse((int) (shortInterval * 1000d), true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void longSign() {
        try {
            pin.pulse((int) (longInterval * 1000d), true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void pauseInLetter() {
        try {
            pin.low();
            Thread.sleep((int) (3d * 1000d * pauseInterval));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void pauseBetweenLetter() {
        try {
            pin.low();
            Thread.sleep((int) (3d * 1000d * pauseInterval));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void pauseWord() {
        try {
            pin.low();
            Thread.sleep((int) (7d * 1000d * pauseInterval));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
