package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Table(name = "localize_store")
@Getter
@Setter
@Entity
public class LocalizedStore extends BaseEntity {
    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @NotEmpty
    private String description;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    private Locale locale;
}
