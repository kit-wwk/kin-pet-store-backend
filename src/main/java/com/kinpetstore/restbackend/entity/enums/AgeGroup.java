package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AgeGroup {
    CHILD(0),
    ADULT(1),
    SENIOR(2);

    private final Integer value;

    public static AgeGroup of(int value) {
        return Arrays.stream(AgeGroup.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}