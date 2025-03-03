package com.capgemini.backend.SpringBoot.application.service.prestamos;

import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    List<Prestamo> getAllPrestamos();
    Optional<Prestamo> getPrestamoById(Long id);
    Prestamo createPrestamo(Prestamo prestamo);
    Prestamo updatePrestamo(Long id, Prestamo prestamo);
    boolean deletePrestamo(Long id);
    List<Prestamo> findByNombreJuegoAndNombreCliente(String nombreJuego, String nombreCliente);
    List<Prestamo> findByFecha(LocalDate fecha);
}