package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PetType {
    DOG(0),
    CAT(1);

    private final Integer value;

    public static PetType of(int value) {
        return Arrays.stream(PetType.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
