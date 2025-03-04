package com.capgemini.backend.SpringBoot.presentation.controller.prestamos;

import com.capgemini.backend.SpringBoot.application.service.prestamos.PrestamoService;
import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoService.getAllPrestamos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.getPrestamoById(id);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo createdPrestamo = prestamoService.createPrestamo(prestamo);
            return new ResponseEntity<>(createdPrestamo, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Prestamo updatedPrestamo = prestamoService.updatePrestamo(id, prestamo);
        if (updatedPrestamo != null) {
            return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        if (prestamoService.deletePrestamo(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter")
    public Page<Prestamo> findByFilters(
            @RequestParam(required = false) String nombreJuego,
            @RequestParam(required = false) String nombreCliente,
            @RequestParam(required = false) LocalDate fecha,
            Pageable pageable) {
        return prestamoService.findByFilters(nombreJuego, nombreCliente, fecha, pageable);
    }
}