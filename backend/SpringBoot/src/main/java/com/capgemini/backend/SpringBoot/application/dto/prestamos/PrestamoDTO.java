package com.capgemini.backend.SpringBoot.application.dto.prestamos;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long id;
    private String nombreJuego;
    private String nombreCliente;
    private LocalDate fechaCreacion;
    private LocalDate fechaDevolucion;

    // Constructor
    public PrestamoDTO(Long id, String nombreJuego, String nombreCliente, LocalDate fechaCreacion, LocalDate fechaDevolucion) {
        this.id = id;
        this.nombreJuego = nombreJuego;
        this.nombreCliente = nombreCliente;
        this.fechaCreacion = fechaCreacion;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}