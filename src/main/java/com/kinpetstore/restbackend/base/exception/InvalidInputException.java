package com.kinpetstore.restbackend.base.exception;

import com.kinpetstore.restbackend.constant.ErrorCode;

public class InvalidInputException extends BaseException {

    public InvalidInputException(String messageCode) {
        super(messageCode, ErrorCode.InvalidInput);
    }
}
