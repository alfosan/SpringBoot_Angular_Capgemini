package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class PrestamoMaximoException extends RuntimeException {
    public PrestamoMaximoException(String message) {
        super(message);
    }
}