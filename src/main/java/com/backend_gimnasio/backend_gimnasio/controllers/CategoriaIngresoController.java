package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.CategoriaIngresoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorias-ingresos")
public class CategoriaIngresoController {

    private List<CategoriaIngresoDTO> categorias = new ArrayList<>();

    public CategoriaIngresoController() {
        categorias.add(new CategoriaIngresoDTO(1L, "Membresías", "Ingresos por membresías", 1L, LocalDate.now()));
        categorias.add(new CategoriaIngresoDTO(2L, "Ventas", "Ingresos por productos vendidos", 2L, LocalDate.now()));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaIngresoDTO>> obtenerTodos() {
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaIngresoDTO> obtenerPorId(@PathVariable Long id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaIngresoDTO> crearCategoria(@RequestBody CategoriaIngresoDTO categoriaDTO) {
        categoriaDTO.setId((long) (categorias.size() + 1));
        categoriaDTO.setFecha(LocalDate.now());
        categorias.add(categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaIngresoDTO> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaIngresoDTO categoriaDTO) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId().equals(id)) {
                categoriaDTO.setId(id);
                categoriaDTO.setFecha(categorias.get(i).getFecha());
                categorias.set(i, categoriaDTO);
                return ResponseEntity.ok(categoriaDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        boolean removed = categorias.removeIf(c -> c.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
