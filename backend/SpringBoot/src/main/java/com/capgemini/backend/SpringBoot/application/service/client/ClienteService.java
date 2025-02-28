package com.capgemini.backend.SpringBoot.application.service.client;

import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Long id);
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    boolean deleteCliente(Long id);
}
