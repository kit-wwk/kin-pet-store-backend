package com.kinpetstore.restbackend.base.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseErrorResponse {
    private Long timestamp;
    private String code;
    private String message;

    public static BaseErrorResponse build(String message, String code) {
        return BaseErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(message)
                .code(code)
                .build();
    }
}
