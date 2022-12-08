package com.kinpetstore.restbackend.domain.request;

import com.kinpetstore.restbackend.entity.enums.StoreStatus;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSearchRequest {
    private String phoneNumber;
    private String facebookUrl;
    private String instagramUrl;
    private String webUrl;
    private Double latitude;
    private Double longitude;
    private Double distanceInKm;
    private Long districtId;
    private StoreStatus status;
}
