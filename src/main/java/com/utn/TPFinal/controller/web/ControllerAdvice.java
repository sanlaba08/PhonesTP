package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.dto.ErrorResponseDto;
import com.utn.TPFinal.exceptions.IncorrectDataClientPhoneException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IncorrectDataClientPhoneException.class)
    public ErrorResponseDto handleLoginException(IncorrectDataClientPhoneException exc) {
        return new ErrorResponseDto(1, exc.getCause().getCause().getMessage());
    }


}
