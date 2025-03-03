
package com.capgemini.backend.SpringBoot.infraestructure.jpa.repo_Impl.prestamos;

import com.capgemini.backend.SpringBoot.domain.model.prestamos.Prestamo;
import com.capgemini.backend.SpringBoot.domain.repo.prestamos.PrestamoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositoryImpl extends JpaRepository<Prestamo, Long>, PrestamoRepository {}
