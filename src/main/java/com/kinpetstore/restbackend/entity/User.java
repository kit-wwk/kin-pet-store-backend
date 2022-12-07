package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "user")
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Column
    @NotNull
    @NotEmpty
    private String sub;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String givenName;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String nickname;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String name;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String picture;

    @Column(length = 1000)
    @NotNull
    @NotEmpty
    private String email;

}
