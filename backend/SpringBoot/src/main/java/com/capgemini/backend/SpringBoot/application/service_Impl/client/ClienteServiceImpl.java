package com.capgemini.backend.SpringBoot.application.service_Impl.client;

import com.capgemini.backend.SpringBoot.application.service.client.ClienteService;
import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;
import com.capgemini.backend.SpringBoot.domain.repo.client.ClienteRepository;
import com.capgemini.backend.SpringBoot.application.exception.client.DuplicateClientNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Obtiene todos los clientes almacenados en la base de datos
     */
    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su ID
     */
    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Crea un nuevo cliente en la base de datos
     */
    @Override
    public Cliente createCliente(Cliente cliente) {
        if (clienteRepository.existsByNombre(cliente.getNombre())) {
            throw new DuplicateClientNameException("Ya existe un cliente con el nombre: " + cliente.getNombre());
        }
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente
     */
    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        if (clienteRepository.findById(id).isPresent()) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    /**
     * Elimina un cliente de la base de datos
     */
    @Override
    public boolean deleteCliente(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}