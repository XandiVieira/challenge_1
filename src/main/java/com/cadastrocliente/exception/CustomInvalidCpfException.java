package com.cadastrocliente.exception;

public class CustomInvalidCpfException extends CustomException {

    public CustomInvalidCpfException(String message) {
        super(message);
    }

    public CustomInvalidCpfException(String message, Throwable cause) {
        super(message, cause);
    }
}