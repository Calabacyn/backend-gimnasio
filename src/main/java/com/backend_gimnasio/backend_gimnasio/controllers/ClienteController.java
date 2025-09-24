package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClienteDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    // Lista mockeada de clientes
    private List<ClienteDTO> clientes = new ArrayList<>();

    public ClienteController() {
        // Inicializamos algunos clientes de ejemplo
        clientes.add(new ClienteDTO(1L, "Juan", "Pérez", "juan.perez@gmail.com",
                "12345678", "1122334455", LocalDate.of(1990, 5, 12),
                LocalDate.now(), "Empleado1"));
        clientes.add(new ClienteDTO(2L, "Ana", "Gómez", "ana.gomez@gmail.com",
                "87654321", "1199887766", LocalDate.of(1985, 8, 20),
                LocalDate.now(), "Empleado2"));
        clientes.add(new ClienteDTO(3L, "Carlos", "López", "carlos.lopez@gmail.com",
                "11223344", "1177665544", LocalDate.of(1992, 3, 5),
                LocalDate.now(), "Empleado3"));
    }

    // GET - Listar todos los clientes
    @GetMapping
    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    // GET - Buscar cliente por ID
    @GetMapping("/{id}")
    public ClienteDTO getCliente(@PathVariable Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null); // o lanzar un ResponseStatusException si querés 404
    }

    // POST - Crear nuevo cliente
    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteDTO cliente) {
        cliente.setId((long) (clientes.size() + 1)); // simula ID generado
        cliente.setFechaRegistro(LocalDate.now());    // asigna fecha de registro
        clientes.add(cliente);
        return cliente;
    }

    // PUT - Actualizar cliente
    @PutMapping("/{id}")
    public ClienteDTO actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteActualizado) {
        ClienteDTO cliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (cliente != null) {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setApellido(clienteActualizado.getApellido());
            cliente.setEmail(clienteActualizado.getEmail());
            cliente.setDni(clienteActualizado.getDni());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            cliente.setRegistradoPor(clienteActualizado.getRegistradoPor());
        }
        return cliente;
    }

    // DELETE - Eliminar cliente
    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        boolean removed = clientes.removeIf(c -> c.getId().equals(id));
        return removed ? "Cliente eliminado" : "Cliente no encontrado";
    }
}
