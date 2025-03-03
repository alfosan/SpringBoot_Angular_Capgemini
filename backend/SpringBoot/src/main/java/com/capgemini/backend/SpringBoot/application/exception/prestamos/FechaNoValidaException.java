package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class FechaNoValidaException extends RuntimeException {
    public FechaNoValidaException(String message) {
        super(message);
    }
}