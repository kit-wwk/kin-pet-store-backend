package com.kinpetstore.restbackend.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Breed {
    CAT_SIBERIAN(0),
    CAT_PRGAMUFFIN(1),
    CAT_RAGDOLL(2),
    CAT_TURKISH_VAN(3),
    CAT_MAINE_COON(4),
    CAT_BRITISH_SHORTHAIR(5),
    CAT_BENGAL(6),
    CAT_PIXIEBOB(7),
    CAT_NORWEGIAN_FOREST_CAT(8),
    CAT_AMERICAN_BOBTAIL(9),
    DOG_POODLE(10),
    DOG_AFFENPINCHER(11),
    DOG_AFGHAN_HOUND(12),
    DOG_AIREDALE_TREEIER(13),
    DOG_AKITA(14),
    DOG_ALASKAN_KLEE_KAI(15),
    DOG_ALASKAN_MALAMUTE(16),
    DOG_AUSTRALIAN_CATTLE_DOG(17),
    DOG_AUSTRALIAN_KELPIE(18),
    DOG_BEAGLE(19);

    private final Integer value;

    public static Breed of(int value) {
        return Arrays.stream(Breed.values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow();
    }
}
