package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProviderNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProviderDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProviderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    private final IProviderService providerService;

    public ProviderController(IProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<ProviderDTO> getAll() {
        return providerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getById(@PathVariable Long id) {
        ProviderDTO provider = providerService.getById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));
        return ResponseEntity.ok(provider);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProviderDTO provider) {
        providerService.create(provider);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ProviderDTO provider) {
        providerService.update(provider);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        providerService.delete(id);
    }
}
