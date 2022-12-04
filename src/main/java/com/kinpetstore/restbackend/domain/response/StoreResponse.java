package com.kinpetstore.restbackend.domain.response;

import com.kinpetstore.restbackend.entity.enums.StoreStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String facebookUrl;
    private String instagramUrl;
    private String webUrl;
    private Double latitude;
    private Double longitude;
    private DistrictResponse district;
    private StoreStatus status;
}
