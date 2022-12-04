package com.kinpetstore.restbackend.domain.response;

import com.kinpetstore.restbackend.entity.enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PetResponse {
    private Long id;
    private String name;
    private StoreResponse store;
    private String image;
    private Double price;
    private Integer age;
    private AgeGroup ageGroup;
    private Breed breed;
    private PetType petType;
    private Coat coat;
    private Gender gender;
    private Size size;
    private PetStatus status;
}
