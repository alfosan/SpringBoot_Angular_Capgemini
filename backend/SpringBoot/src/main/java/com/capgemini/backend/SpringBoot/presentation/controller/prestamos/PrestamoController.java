package com.capgemini.backend.SpringBoot.presentation.controller.prestamos;

import com.capgemini.backend.SpringBoot.application.dto.prestamos.PrestamoDTO;
import com.capgemini.backend.SpringBoot.application.exception.prestamos.ClienteNoReconocidoException;
import com.capgemini.backend.SpringBoot.application.exception.prestamos.FechaNoValidaException;
import com.capgemini.backend.SpringBoot.application.exception.prestamos.FiltroFallidoException;
import com.capgemini.backend.SpringBoot.application.exception.prestamos.JuegoNoReconocidoException;
import com.capgemini.backend.SpringBoot.application.exception.prestamos.PrestamoMaximoException;
import com.capgemini.backend.SpringBoot.application.service.prestamos.PrestamoService;
import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import com.capgemini.backend.SpringBoot.presentation.assembler.prestamos.PrestamoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<PrestamoDTO> getAllPrestamos() {
        return prestamoService.getAllPrestamos().stream()
                .map(PrestamoAssembler::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> getPrestamoById(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.getPrestamoById(id);
        return prestamo.map(p -> ResponseEntity.ok(PrestamoAssembler.toDTO(p)))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrestamoDTO> createPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        Prestamo prestamo = PrestamoAssembler.toEntity(prestamoDTO);
        Prestamo nuevoPrestamo = prestamoService.createPrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(PrestamoAssembler.toDTO(nuevoPrestamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoDTO> updatePrestamo(@PathVariable Long id, @RequestBody PrestamoDTO prestamoDTO) {
        Prestamo prestamo = PrestamoAssembler.toEntity(prestamoDTO);
        Prestamo prestamoActualizado = prestamoService.updatePrestamo(id, prestamo);
        return prestamoActualizado != null ? ResponseEntity.ok(PrestamoAssembler.toDTO(prestamoActualizado)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        return prestamoService.deletePrestamo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public Page<PrestamoDTO> filterPrestamos(
            @RequestParam(required = false) String nombreJuego,
            @RequestParam(required = false) String nombreCliente,
            @RequestParam(required = false) LocalDate fecha,
            Pageable pageable) {
        return prestamoService.findByFilters(nombreJuego, nombreCliente, fecha, pageable)
                .map(PrestamoAssembler::toDTO);
    }

    @ExceptionHandler({ClienteNoReconocidoException.class, JuegoNoReconocidoException.class, FiltroFallidoException.class, FechaNoValidaException.class, PrestamoMaximoException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}