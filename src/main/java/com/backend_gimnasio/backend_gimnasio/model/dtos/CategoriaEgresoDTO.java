package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CategoriaEgresoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long idUsuario;
    private LocalDate fecha;

    // Constructor vacío
    public CategoriaEgresoDTO() {
    }

    // Constructor con todos los campos
    public CategoriaEgresoDTO(Long id, String nombre, String descripcion, Long idUsuario, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }
}
