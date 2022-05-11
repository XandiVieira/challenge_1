package com.cadastrocliente.exception;

public class CustomIllegalArgumentException extends CustomException {

    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    public CustomIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}