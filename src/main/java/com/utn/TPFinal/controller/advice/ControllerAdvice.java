package com.utn.TPFinal.controller.advice;

import com.utn.TPFinal.dto.ErrorResponseDto;
import com.utn.TPFinal.exceptions.IncorrectDataClientPhoneException;
import com.utn.TPFinal.exceptions.InvalidLoginException;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginException.class)
    public ErrorResponseDto handleLoginException(InvalidLoginException exc) {
        return new ErrorResponseDto(1, "Invalid login");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAllReadyExistException.class)
    public ResponseEntity handleUserAllReadyExistException(UserAllReadyExistException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
