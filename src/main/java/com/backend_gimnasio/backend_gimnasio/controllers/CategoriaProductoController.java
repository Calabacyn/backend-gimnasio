package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.CategoriaProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private List<CategoriaProductoDTO> categorias = new ArrayList<>();

    public CategoriaProductoController() {
        categorias.add(new CategoriaProductoDTO(1L, "Suplementos", "Proteínas, creatina, aminoácidos"));
        categorias.add(new CategoriaProductoDTO(2L, "Accesorios", "Guantes, bandas, colchonetas"));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProductoDTO>> obtenerTodos() {
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> obtenerPorId(@PathVariable Long id) {
        return categorias.stream()
                .filter(c -> c.getIdCategoria().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaProductoDTO> crearCategoria(@RequestBody CategoriaProductoDTO categoriaDTO) {
        categoriaDTO.setIdCategoria((long) (categorias.size() + 1));
        categorias.add(categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaProductoDTO categoriaDTO) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria().equals(id)) {
                categoriaDTO.setIdCategoria(id);
                categorias.set(i, categoriaDTO);
                return ResponseEntity.ok(categoriaDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        boolean removed = categorias.removeIf(c -> c.getIdCategoria().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
