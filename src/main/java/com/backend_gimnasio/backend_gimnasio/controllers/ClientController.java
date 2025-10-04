package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getBy(@PathVariable Long id) {
        ClientDTO client = clientService.getBy(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(client);
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ClientDTO client) {
        clientService.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ClientDTO client) {
        clientService.update(client);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
