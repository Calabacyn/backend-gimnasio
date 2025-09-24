package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDate fechaActualizacion;
    private Long idCategoria;

    // Constructor vacío
    public ProductoDTO() {
    }

    // Constructor con todos los campos
    public ProductoDTO(Long idProducto, String nombre, String descripcion, BigDecimal precio,
                       LocalDate fechaActualizacion, Long idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaActualizacion = fechaActualizacion;
        this.idCategoria = idCategoria;
    }
}
