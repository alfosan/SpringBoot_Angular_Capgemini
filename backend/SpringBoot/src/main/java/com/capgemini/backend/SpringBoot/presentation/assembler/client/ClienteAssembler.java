package com.capgemini.backend.SpringBoot.presentation.assembler.client;

import com.capgemini.backend.SpringBoot.application.dto.client.ClienteDTO;
import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;

public class ClienteAssembler {

    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre());
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNombre());
    }
}
