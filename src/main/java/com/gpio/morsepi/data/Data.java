package com.gpio.morsepi.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Data {
    public boolean active;
    public String text;
}
