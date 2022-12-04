package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import com.kinpetstore.restbackend.domain.response.PetResponse;
import com.kinpetstore.restbackend.entity.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Locale;

@Table(name = "pet")
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends BaseEntity {
    @NotNull
    @NotEmpty
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @NotNull
    private Store store;

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

    public PetResponse toResponse(Locale locale) {
        return PetResponse.builder()
                .id(getId())
                .name(name)
                .store(store.toResponse(locale))
                .image(image)
                .price(price)
                .age(age)
                .ageGroup(ageGroup)
                .breed(breed)
                .petType(petType)
                .coat(coat)
                .gender(gender)
                .size(size)
                .status(status)
                .build();
    }
}
