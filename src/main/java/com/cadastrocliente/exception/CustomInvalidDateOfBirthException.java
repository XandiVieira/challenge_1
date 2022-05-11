package com.cadastrocliente.exception;

public class CustomInvalidDateOfBirthException extends CustomException {

    public CustomInvalidDateOfBirthException(String message) {
        super(message);
    }

    public CustomInvalidDateOfBirthException(String message, Throwable cause) {
        super(message, cause);
    }
}