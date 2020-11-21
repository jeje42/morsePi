package com.gpio.morsepi;

import com.gpio.morsepi.gpio.GpioWrapper;
import com.gpio.morsepi.runner.StartUpRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MorsepiApplicationTests {

	@MockBean
	private GpioWrapper gpioWrapper;

	@MockBean
	private StartUpRunner startUpRunner;

	@Test
	void contextLoads() {
	}

}
