package com.capgemini.backend.SpringBoot.presentation.assembler.prestamos;

import com.capgemini.backend.SpringBoot.application.dto.prestamos.PrestamoDTO;
import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;

public class PrestamoAssembler {

    public static PrestamoDTO toDTO(Prestamo prestamo) {
        return new PrestamoDTO(prestamo.getId(), prestamo.getNombreJuego(), prestamo.getNombreCliente(), prestamo.getFechaCreacion(), prestamo.getFechaDevolucion());
    }

    public static Prestamo toEntity(PrestamoDTO prestamoDTO) {
        return new Prestamo(prestamoDTO.getId(), prestamoDTO.getNombreJuego(), prestamoDTO.getNombreCliente(), prestamoDTO.getFechaCreacion(), prestamoDTO.getFechaDevolucion());
    }
}