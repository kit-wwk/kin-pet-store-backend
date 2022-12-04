package com.kinpetstore.restbackend.base.controller;

import com.kinpetstore.restbackend.base.domain.BaseErrorResponse;
import com.kinpetstore.restbackend.base.domain.BaseResponse;
import com.kinpetstore.restbackend.base.exception.BaseException;
import com.kinpetstore.restbackend.constant.MessageCode;
import com.kinpetstore.restbackend.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.kinpetstore.restbackend.constant.ErrorCode.PayloadConstraintViolation;

@Log4j2
@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {

    @Autowired
    MessageService messageService;

    @ExceptionHandler({Exception.class})
    protected BaseResponse<String> handleError(final HttpServletRequest req, final Exception ex) {
        logger.error("Error:", ex);
        try {
            String message = messageService.getMessage(MessageCode.Exception.Generic, null, req.getLocale());
            return BaseResponse.error(message);
        } catch (NoSuchMessageException e) {

        }
        return BaseResponse.error(MessageCode.Exception.Generic);
    }

    @ExceptionHandler({BaseException.class})
    protected BaseErrorResponse handleError(final HttpServletRequest req, final BaseException ex) {
        logger.error("Error:", ex);
        try {
            String message = messageService.getMessage(ex.getMessageCode(), null, req.getLocale());
            return BaseErrorResponse.build(message, ex.getErrorCode());
        } catch (NoSuchMessageException e) {

        }
        return BaseErrorResponse.build(ex.getMessageCode(), ex.getErrorCode());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected BaseErrorResponse handleError(final HttpServletRequest req, final ConstraintViolationException ex) {
        logger.error("Error:", ex);
        String messageTemplate = ex.getConstraintViolations().stream().findFirst().get().getMessageTemplate().replaceAll("\\{|\\}", "").toUpperCase();
        try {
            String message = messageService.getMessage(messageTemplate, null, req.getLocale());
            return BaseErrorResponse.build(message, PayloadConstraintViolation);
        } catch (NoSuchMessageException e) {

        }
        return BaseErrorResponse.build(messageTemplate, PayloadConstraintViolation);
    }

}
