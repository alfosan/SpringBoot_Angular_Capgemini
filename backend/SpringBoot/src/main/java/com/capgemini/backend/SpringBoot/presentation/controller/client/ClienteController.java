package com.capgemini.backend.SpringBoot.presentation.controller.client;

import com.capgemini.backend.SpringBoot.application.dto.client.ClienteDTO;
import com.capgemini.backend.SpringBoot.application.service.client.ClienteService;
import com.capgemini.backend.SpringBoot.domain.model.client.Cliente;
import com.capgemini.backend.SpringBoot.presentation.assembler.client.ClienteAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.getAllClientes().stream()
                .map(ClienteAssembler::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(c -> ResponseEntity.ok(ClienteAssembler.toDTO(c)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteAssembler.toEntity(clienteDTO);
        Cliente nuevoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteAssembler.toDTO(nuevoCliente));
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteAssembler.toEntity(clienteDTO);
        Cliente clienteActualizado = clienteService.updateCliente(id, cliente);
        return clienteActualizado != null ? ResponseEntity.ok(ClienteAssembler.toDTO(clienteActualizado)) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
