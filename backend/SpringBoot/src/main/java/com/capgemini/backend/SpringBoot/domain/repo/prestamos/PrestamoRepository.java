package com.capgemini.backend.SpringBoot.domain.repo.prestamos;

import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByNombreJuegoContainingAndNombreClienteContaining(String nombreJuego, String nombreCliente);
    List<Prestamo> findByFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin);
}