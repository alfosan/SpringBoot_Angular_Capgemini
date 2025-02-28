package com.capgemini.backend.SpringBoot.application.exception.games;

public class DuplicateGameTitleException extends RuntimeException {
    public DuplicateGameTitleException(String message) {
        super(message);
    }
}