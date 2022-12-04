package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Coat {
    SHORT(0),
    MEDIUM(1),
    LONG(2);

    private final Integer value;

    public static Coat of(int value) {
        return Arrays.stream(Coat.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
