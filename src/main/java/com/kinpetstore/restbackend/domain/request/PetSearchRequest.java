package com.kinpetstore.restbackend.domain.request;

import com.kinpetstore.restbackend.entity.enums.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetSearchRequest {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer minAge;
    private Integer maxAge;
    private AgeGroup ageGroup;
    private Breed breed;
    private PetType petType;
    private Coat coat;
    private Gender gender;
    private Size size;
    private PetStatus status;
    private Long storeId;
}
