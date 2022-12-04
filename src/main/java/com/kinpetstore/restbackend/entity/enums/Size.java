package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Size {
    SMALL(0),
    MEDIUM(1),
    LARGE(2);

    private final Integer value;

    public static Size of(int value) {
        return Arrays.stream(Size.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
