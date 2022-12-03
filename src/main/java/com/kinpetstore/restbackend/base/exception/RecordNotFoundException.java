package com.kinpetstore.restbackend.base.exception;

import com.kinpetstore.restbackend.constant.ErrorCode;

public class RecordNotFoundException extends BaseException {

    public RecordNotFoundException(String messageCode) {
        super(messageCode, ErrorCode.RecordNotFound);
    }
}
