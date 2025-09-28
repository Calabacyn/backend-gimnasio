package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.MembresiaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    private List<MembresiaDTO> membresias = new ArrayList<>();

    public MembresiaController() {
        membresias.add(new MembresiaDTO(1L, "Membresía Básica", "Mensual", 1000.0, "Acceso limitado", 30));
        membresias.add(new MembresiaDTO(2L, "Membresía Premium", "Mensual", 2000.0, "Acceso completo + clases", 30));
    }

    @GetMapping
    public ResponseEntity<List<MembresiaDTO>> obtenerTodas() {
        return ResponseEntity.ok(membresias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDTO> obtenerPorId(@PathVariable Long id) {
        return membresias.stream()
                .filter(m -> m.getIdMembresia().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MembresiaDTO> crearMembresia(@RequestBody MembresiaDTO membresiaDTO) {
        membresiaDTO.setIdMembresia((long) (membresias.size() + 1));
        membresias.add(membresiaDTO);
        return ResponseEntity.ok(membresiaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDTO> actualizarMembresia(@PathVariable Long id, @RequestBody MembresiaDTO membresiaDTO) {
        for (int i = 0; i < membresias.size(); i++) {
            if (membresias.get(i).getIdMembresia().equals(id)) {
                membresiaDTO.setIdMembresia(id);
                membresias.set(i, membresiaDTO);
                return ResponseEntity.ok(membresiaDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMembresia(@PathVariable Long id) {
        boolean removed = membresias.removeIf(m -> m.getIdMembresia().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
