package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaProductoDTO {

    private Long idCategoria;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public CategoriaProductoDTO() {
    }

    // Constructor con todos los campos
    public CategoriaProductoDTO(Long idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
