package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorDTO {

    private Long idProveedor;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public ProveedorDTO() {
    }

    // Constructor con todos los campos
    public ProveedorDTO(Long idProveedor, String nombre, String descripcion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
