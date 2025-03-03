package com.capgemini.backend.SpringBoot.infraestructure.jpa.repo_Impl.client;

import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;
import com.capgemini.backend.SpringBoot.domain.repo.client.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<Cliente, Long>, ClienteRepository {}
