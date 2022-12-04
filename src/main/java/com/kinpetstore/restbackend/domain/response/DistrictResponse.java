package com.kinpetstore.restbackend.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DistrictResponse {
    private Long id;
    private String districtCode;
    private String name;
}
