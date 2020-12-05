package com.mtotowamkwe.lostboyzemailservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(EmailNotSentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String emailNotSentHandler(EmailNotSentException ense) {
        return ense.getMessage();
    }

}
