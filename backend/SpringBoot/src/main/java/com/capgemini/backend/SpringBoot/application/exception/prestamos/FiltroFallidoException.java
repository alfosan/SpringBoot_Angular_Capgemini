package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class FiltroFallidoException extends RuntimeException {
    public FiltroFallidoException(String message) {
        super(message);
    }
}