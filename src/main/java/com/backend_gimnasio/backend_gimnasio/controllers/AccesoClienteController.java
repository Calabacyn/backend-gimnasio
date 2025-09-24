package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.AccesoClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accesos-clientes")
public class AccesoClienteController {

    private List<AccesoClienteDTO> accesos = new ArrayList<>();

    public AccesoClienteController() {
        accesos.add(new AccesoClienteDTO(1L, 101L, LocalDate.now(), LocalTime.of(8, 0), LocalTime.of(10, 0), 1L, "activo"));
        accesos.add(new AccesoClienteDTO(2L, 102L, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(11, 0), 2L, "inactivo"));
    }

    @GetMapping
    public ResponseEntity<List<AccesoClienteDTO>> obtenerTodos() {
        return ResponseEntity.ok(accesos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesoClienteDTO> obtenerPorId(@PathVariable Long id) {
        return accesos.stream()
                .filter(a -> a.getIdAcceso().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccesoClienteDTO> crearAcceso(@RequestBody AccesoClienteDTO accesoDTO) {
        accesoDTO.setIdAcceso((long) (accesos.size() + 1));
        accesos.add(accesoDTO);
        return ResponseEntity.ok(accesoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccesoClienteDTO> actualizarAcceso(@PathVariable Long id, @RequestBody AccesoClienteDTO accesoDTO) {
        for (int i = 0; i < accesos.size(); i++) {
            if (accesos.get(i).getIdAcceso().equals(id)) {
                accesoDTO.setIdAcceso(id);
                accesos.set(i, accesoDTO);
                return ResponseEntity.ok(accesoDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAcceso(@PathVariable Long id) {
        boolean removed = accesos.removeIf(a -> a.getIdAcceso().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
