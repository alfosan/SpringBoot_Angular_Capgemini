package com.capgemini.backend.SpringBoot.application.exception.client;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
