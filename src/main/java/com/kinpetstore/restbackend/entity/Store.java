package com.kinpetstore.restbackend.entity.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "store")
@Getter
@Setter
public class Store implements Base {
    @Id
    private Long id;
}
