package com.capgemini.backend.SpringBoot.domain.repo.client;

import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByNombre(String nombre);
}
