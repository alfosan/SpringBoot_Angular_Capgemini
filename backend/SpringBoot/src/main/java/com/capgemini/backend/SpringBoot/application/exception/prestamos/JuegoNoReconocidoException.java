package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class JuegoNoReconocidoException extends RuntimeException {
    public JuegoNoReconocidoException(String message) {
        super(message);
    }
}