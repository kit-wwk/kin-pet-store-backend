package com.kinpetstore.restbackend.base.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class BaseResponse<T> {
    private Long timestamp;
    private Integer status;
    private String message;
    private T data;


    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .timestamp(System.currentTimeMillis())
                .status(Status.SUCCESS.code)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(String errMsg) {
        return BaseResponse.<T>builder()
                .timestamp(System.currentTimeMillis())
                .status(Status.ERROR.code)
                .message(errMsg)
                .build();
    }

    public enum Status {
        SUCCESS(0), ERROR(1);

        private final Integer code;

        Status(Integer code) {
            this.code = code;
        }

        public static Status codeOf(Integer code) {
            return Arrays.stream(Status.values())
                    .filter(i -> i.code.equals(code))
                    .findFirst()
                    .orElse(null);
        }
    }
}
