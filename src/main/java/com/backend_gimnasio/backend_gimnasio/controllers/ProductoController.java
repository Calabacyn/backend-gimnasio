package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<ProductoDTO> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new ProductoDTO(1L, "Proteína Whey", "Proteína de suero", new BigDecimal("3500"), LocalDate.now(), 1L));
        productos.add(new ProductoDTO(2L, "Creatina Monohidratada", "Suplemento para fuerza", new BigDecimal("1500"), LocalDate.now(), 1L));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return productos.stream()
                .filter(p -> p.getIdProducto().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        productoDTO.setIdProducto((long) (productos.size() + 1));
        productoDTO.setFechaActualizacion(LocalDate.now());
        productos.add(productoDTO);
        return ResponseEntity.ok(productoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto().equals(id)) {
                productoDTO.setIdProducto(id);
                productoDTO.setFechaActualizacion(LocalDate.now());
                productos.set(i, productoDTO);
                return ResponseEntity.ok(productoDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean removed = productos.removeIf(p -> p.getIdProducto().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
