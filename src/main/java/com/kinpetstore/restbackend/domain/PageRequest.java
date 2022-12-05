package com.kinpetstore.restbackend.domain;

import lombok.Data;

@Data
public class PageRequest {
    private Integer pageSize;
    private Integer pageNo;
}
