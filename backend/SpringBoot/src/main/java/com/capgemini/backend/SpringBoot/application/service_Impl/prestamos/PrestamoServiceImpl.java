package com.capgemini.backend.SpringBoot.application.service_Impl.prestamos;

import com.capgemini.backend.SpringBoot.application.exception.prestamos.*;
import com.capgemini.backend.SpringBoot.application.service.client.ClienteService;
import com.capgemini.backend.SpringBoot.application.service.games.GameService;
import com.capgemini.backend.SpringBoot.application.service.prestamos.PrestamoService;
import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import com.capgemini.backend.SpringBoot.domain.repo.prestamos.PrestamoRepository;
import com.capgemini.backend.SpringBoot.infraestructure.common.criteria.SearchCriteria;
import com.capgemini.backend.SpringBoot.infraestructure.jpa.repo_Impl.prestamos.PrestamoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private GameService gameService;

    @Override
    public List<Prestamo> getAllPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> getPrestamoById(Long id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo createPrestamo(Prestamo prestamo) {
        // Validar que el cliente y el juego existen
        if (!clienteExiste(prestamo.getNombreCliente())) {
            throw new ClienteNoReconocidoException("Cliente no reconocido: " + prestamo.getNombreCliente());
        }
        if (!juegoExiste(prestamo.getNombreJuego())) {
            throw new JuegoNoReconocidoException("Juego no reconocido: " + prestamo.getNombreJuego());
        }

        // Validar que la fecha de fin no es anterior a la fecha de inicio
        if (prestamo.getFechaDevolucion().isBefore(prestamo.getFechaCreacion())) {
            throw new FechaNoValidaException("La fecha de devolución no puede ser anterior a la fecha de creación.");
        }

        // Validar que el periodo de préstamo no excede los 14 días
        long diasPrestamo = ChronoUnit.DAYS.between(prestamo.getFechaCreacion(), prestamo.getFechaDevolucion());
        if (diasPrestamo > 13) {
            throw new PrestamoMaximoException("El periodo de préstamo no puede exceder los 14 días.");
        }

        // Validar que el mismo juego no está prestado a dos clientes distintos en el mismo día
        if (juegoPrestadoEnPeriodo(prestamo.getNombreJuego(), prestamo.getFechaCreacion(), prestamo.getFechaDevolucion())) {
            throw new PrestamoMaximoException("El juego ya está prestado a otro cliente en el mismo periodo.");
        }

        // Validar que el mismo cliente no tiene más de 2 juegos prestados en el mismo día
        if (clienteConMasDeDosPrestamos(prestamo.getNombreCliente(), prestamo.getFechaCreacion(), prestamo.getFechaDevolucion())) {
            throw new PrestamoMaximoException("El cliente ya tiene más de 2 juegos prestados en el mismo periodo.");
        }

        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo updatePrestamo(Long id, Prestamo prestamo) {
        if (prestamoRepository.findById(id).isPresent()) {
            prestamo.setId(id);
            return prestamoRepository.save(prestamo);
        }
        return null;
    }

    @Override
    public boolean deletePrestamo(Long id) {
        if (prestamoRepository.findById(id).isPresent()) {
            prestamoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<Prestamo> findByNombreJuegoAndNombreCliente(String nombreJuego, String nombreCliente, Pageable pageable) {
        Page<Prestamo> prestamos = prestamoRepository.findByNombreJuegoContainingAndNombreClienteContaining(nombreJuego, nombreCliente, pageable);
        if (prestamos.isEmpty()) {
            throw new FiltroFallidoException("No se encontraron préstamos con los filtros proporcionados.");
        }
        return prestamos;
    }

    @Override
    public Page<Prestamo> findByFecha(LocalDate fecha, Pageable pageable) {
        Page<Prestamo> prestamos = prestamoRepository.findByFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(fecha, fecha, pageable);
        if (prestamos.isEmpty()) {
            throw new FechaNoValidaException("No se encontraron préstamos para la fecha proporcionada.");
        }
        return prestamos;
    }

    @Override
    public Page<Prestamo> findByFilters(String nombreJuego, String nombreCliente, LocalDate fecha, Pageable pageable) {
        Specification<Prestamo> spec = Specification.where(null);

        if (nombreJuego != null && !nombreJuego.isEmpty()) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("nombreJuego", ":", nombreJuego)));
        }
        if (nombreCliente != null && !nombreCliente.isEmpty()) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("nombreCliente", ":", nombreCliente)));
        }
        if (fecha != null) {
            spec = spec.and(new PrestamoSpecification(new SearchCriteria("fechaCreacion", ":", fecha)));
        }

        Page<Prestamo> prestamos = prestamoRepository.findAll(spec, pageable);

        if (prestamos.isEmpty()) {
            throw new FiltroFallidoException("No se encontraron préstamos con los filtros proporcionados.");
        }

        return prestamos;
    }

    private boolean clienteExiste(String nombreCliente) {
        return clienteService.getAllClientes().stream()
                .anyMatch(cliente -> cliente.getNombre().equals(nombreCliente));
    }

    private boolean juegoExiste(String nombreJuego) {
        return gameService.getAllGames().stream()
                .anyMatch(game -> game.getTitulo().equals(nombreJuego));
    }

    private boolean juegoPrestadoEnPeriodo(String nombreJuego, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Prestamo> prestamos = prestamoRepository.findByNombreJuegoContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
                nombreJuego, fechaFin, fechaInicio);
        return !prestamos.isEmpty();
    }

    private boolean clienteConMasDeDosPrestamos(String nombreCliente, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Prestamo> prestamos = prestamoRepository.findByNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
                nombreCliente, fechaFin, fechaInicio);
        return prestamos.size() > 1;
    }
}