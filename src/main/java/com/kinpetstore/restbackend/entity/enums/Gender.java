package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {
    MALE(0),
    FEMALE(1);

    private final Integer value;

    public static Gender of(int value) {
        return Arrays.stream(Gender.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
