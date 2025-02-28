package com.capgemini.backend.SpringBoot.application.exception.client;

public class DuplicateClientNameException extends RuntimeException {
    public DuplicateClientNameException(String message) {
        super(message);
    }
}
