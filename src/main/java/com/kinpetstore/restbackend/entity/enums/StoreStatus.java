package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StoreStatus {
    INACTIVE(0),
    ACTIVE(1);

    private final Integer value;

    public static StoreStatus of(int value) {
        return Arrays.stream(StoreStatus.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
