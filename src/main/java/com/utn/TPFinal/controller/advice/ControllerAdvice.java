package com.utn.TPFinal.controller.advice;

import com.utn.TPFinal.dto.ErrorResponseDto;
import com.utn.TPFinal.exceptions.*;
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginException.class)
    public ErrorResponseDto handleLoginException(InvalidLoginException exc) {
        return new ErrorResponseDto(2, "Invalid login");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAllReadyExistException.class)
    public ErrorResponseDto handleUserAllReadyExistException(UserAllReadyExistException e) {
        return new ErrorResponseDto(3, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotExistException.class)
    public ErrorResponseDto handleUserNotExistException(UserNotExistException e) {
        return new ErrorResponseDto(4, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeException.class)
    public ErrorResponseDto handleEmployeeException(EmployeeException e) {
        return new ErrorResponseDto(5, e.getMessage());
    }


}
