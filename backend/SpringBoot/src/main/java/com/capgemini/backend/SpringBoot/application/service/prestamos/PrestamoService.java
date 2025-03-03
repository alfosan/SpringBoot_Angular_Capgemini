package com.capgemini.backend.SpringBoot.application.service.prestamos;

import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    List<Prestamo> getAllPrestamos();
    Optional<Prestamo> getPrestamoById(Long id);
    Prestamo createPrestamo(Prestamo prestamo);
    Prestamo updatePrestamo(Long id, Prestamo prestamo);
    boolean deletePrestamo(Long id);
    Page<Prestamo> findByNombreJuegoAndNombreCliente(String nombreJuego, String nombreCliente, Pageable pageable);
    Page<Prestamo> findByFecha(LocalDate fecha, Pageable pageable);
    Page<Prestamo> findByFilters(String nombreJuego, String nombreCliente, LocalDate fecha, Pageable pageable);
}