package com.capgemini.backend.SpringBoot.application.service_Impl.prestamos;

import com.capgemini.backend.SpringBoot.application.service.prestamos.PrestamoService;
import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import com.capgemini.backend.SpringBoot.domain.repo.prestamos.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

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
}