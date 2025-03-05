package com.capgemini.backend.SpringBoot.domain.repo.prestamos;

import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
    Page<Prestamo> findByNombreJuegoContainingAndNombreClienteContaining(String nombreJuego, String nombreCliente, Pageable pageable);
    Page<Prestamo> findByFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);
    Page<Prestamo> findByNombreJuegoContainingAndNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreJuego, String nombreCliente, LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    List<Prestamo> findByNombreJuegoContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreJuego, LocalDate fechaFin, LocalDate fechaInicio);

    List<Prestamo> findByNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreCliente, LocalDate fechaFin, LocalDate fechaInicio);
}