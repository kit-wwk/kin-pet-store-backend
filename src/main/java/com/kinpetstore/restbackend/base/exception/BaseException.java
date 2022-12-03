package com.kinpetstore.restbackend.base.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends Exception {
    private String messageCode = "";
    private String errorCode = "";

    public BaseException(String messageCode, String errorCode) {
        super();

        this.setMessageCode(messageCode);
        this.setErrorCode(errorCode);
    }
}
