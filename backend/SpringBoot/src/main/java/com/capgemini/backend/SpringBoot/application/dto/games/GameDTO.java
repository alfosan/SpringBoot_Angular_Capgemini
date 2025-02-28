package com.capgemini.backend.SpringBoot.application.dto.games;

public class GameDTO {

    private Long id;
    private String titulo;

    // Constructor
    public GameDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}