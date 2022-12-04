package com.kinpetstore.restbackend.domain.request;

import com.kinpetstore.restbackend.entity.LocalizedStore;
import com.kinpetstore.restbackend.entity.enums.StoreStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class StoreRequest {
    private Long id;
    @NotNull
    private List<LocalizedStore> localizedStore;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String facebookUrl;

    @NotNull
    @NotEmpty
    private String instagramUrl;

    @NotNull
    @NotEmpty
    private String webUrl;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Long districtId;

    @NotNull
    private StoreStatus status;
}