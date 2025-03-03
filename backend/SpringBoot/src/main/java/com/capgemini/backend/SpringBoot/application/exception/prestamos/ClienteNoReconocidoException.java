package com.capgemini.backend.SpringBoot.application.exception.prestamos;

public class ClienteNoReconocidoException extends RuntimeException {
    public ClienteNoReconocidoException(String message) {
        super(message);
    }
}