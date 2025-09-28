package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.PlanificacionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/planificaciones")
public class PlanificacionController {

    private List<PlanificacionDTO> planificaciones = new ArrayList<>();

    public PlanificacionController() {
        planificaciones.add(new PlanificacionDTO(1L, 1L, "Plan A", "Descripción Plan A", LocalDate.now(), 1L, 1L, LocalDate.now().plusMonths(1)));
        planificaciones.add(new PlanificacionDTO(2L, 2L, "Plan B", "Descripción Plan B", LocalDate.now(), 2L, 1L, LocalDate.now().plusMonths(2)));
    }

    @GetMapping
    public ResponseEntity<List<PlanificacionDTO>> obtenerTodos() {
        return ResponseEntity.ok(planificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanificacionDTO> obtenerPorId(@PathVariable Long id) {
        return planificaciones.stream()
                .filter(p -> p.getIdPlanificacion().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlanificacionDTO> crearPlanificacion(@RequestBody PlanificacionDTO planificacionDTO) {
        planificacionDTO.setIdPlanificacion((long) (planificaciones.size() + 1));
        planificaciones.add(planificacionDTO);
        return ResponseEntity.ok(planificacionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanificacionDTO> actualizarPlanificacion(@PathVariable Long id, @RequestBody PlanificacionDTO planificacionDTO) {
        for (int i = 0; i < planificaciones.size(); i++) {
            if (planificaciones.get(i).getIdPlanificacion().equals(id)) {
                planificacionDTO.setIdPlanificacion(id);
                planificaciones.set(i, planificacionDTO);
                return ResponseEntity.ok(planificacionDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlanificacion(@PathVariable Long id) {
        boolean removed = planificaciones.removeIf(p -> p.getIdPlanificacion().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
