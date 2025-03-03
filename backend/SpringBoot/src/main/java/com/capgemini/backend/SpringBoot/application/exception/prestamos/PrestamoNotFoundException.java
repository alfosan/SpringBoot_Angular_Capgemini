package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class PrestamoNotFoundException extends RuntimeException {
    public PrestamoNotFoundException(String message) {
        super(message);
    }
}