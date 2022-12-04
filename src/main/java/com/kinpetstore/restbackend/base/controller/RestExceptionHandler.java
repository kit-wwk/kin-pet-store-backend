package com.kinpetstore.restbackend.base.controller;

import com.kinpetstore.restbackend.base.domain.BaseErrorResponse;
import com.kinpetstore.restbackend.constant.DefaultSetting;
import com.kinpetstore.restbackend.constant.ErrorCode;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageService messageService;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("Error:", ex);
        try {
            String message = messageService.getMessage(MessageCode.Exception.InvalidPayload, null, DefaultSetting.DEFAULT_LOCALE);
            return new ResponseEntity<>(BaseErrorResponse.build(message, ErrorCode.InvalidPayload), status);
        } catch (NoSuchMessageException e) {

        }
        return new ResponseEntity<>(BaseErrorResponse.build(MessageCode.Exception.InvalidPayload, ErrorCode.InvalidPayload), status);

    }

}
