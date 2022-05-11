package com.cadastrocliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            CustomInvalidCpfException.class
    })
    public ResponseEntity<Object> cpfInvalid(CustomInvalidCpfException exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {
            CustomInvalidDateOfBirthException.class
    })
    public ResponseEntity<Object> dateOfBirthInvalid(CustomInvalidDateOfBirthException exception) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {
            CustomAlreadyExistsException.class
    })
    public ResponseEntity<Object> duplicatedCpf(CustomAlreadyExistsException exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {
            CustomIllegalArgumentException.class
    })
    public ResponseEntity<Object> duplicatedCpf(CustomIllegalArgumentException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException = new ApiException(exception.getMessage(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, httpStatus);
    }
}