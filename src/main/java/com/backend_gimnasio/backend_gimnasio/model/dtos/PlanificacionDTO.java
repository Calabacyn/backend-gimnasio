package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter// genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // genera constructor vacío
@AllArgsConstructor // genera constructor con todos los campos

public class PlanificacionDTO {

    private Long idPlanificacion;
    private Long idCliente;
    private String planificacion;
    private String descripcion;
    private LocalDate fecha; // fecha de creación
    private Long idMembresia;
    private Long idUsuario; // usuario que cargó el registro
    private LocalDate fechaVencimiento; // fecha de vencimiento/duración
}
