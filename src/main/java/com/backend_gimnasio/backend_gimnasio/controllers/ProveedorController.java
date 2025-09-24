package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProveedorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private List<ProveedorDTO> proveedores = new ArrayList<>();

    public ProveedorController() {
        proveedores.add(new ProveedorDTO(1L, "Proveedor A", "Descripción A"));
        proveedores.add(new ProveedorDTO(2L, "Proveedor B", "Descripción B"));
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> obtenerTodos() {
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Long id) {
        return proveedores.stream()
                .filter(p -> p.getIdProveedor().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crearProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        proveedorDTO.setIdProveedor((long) (proveedores.size() + 1));
        proveedores.add(proveedorDTO);
        return ResponseEntity.ok(proveedorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizarProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getIdProveedor().equals(id)) {
                proveedorDTO.setIdProveedor(id);
                proveedores.set(i, proveedorDTO);
                return ResponseEntity.ok(proveedorDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        boolean removed = proveedores.removeIf(p -> p.getIdProveedor().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
