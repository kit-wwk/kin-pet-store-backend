package com.kinpetstore.restbackend.domain.request;

import com.kinpetstore.restbackend.entity.enums.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    @NotNull
    private Long storeId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String image;

    @NotNull
    private Double price;

    @NotNull
    private Integer age;

    @NotNull
    private AgeGroup ageGroup;

    @NotNull
    private Breed breed;

    @NotNull
    private PetType petType;

    @NotNull
    private Coat coat;

    @NotNull
    private Gender gender;

    @NotNull
    private Size size;

    @NotNull
    private PetStatus status;
}
