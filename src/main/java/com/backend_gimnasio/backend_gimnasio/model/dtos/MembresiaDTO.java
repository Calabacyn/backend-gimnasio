package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MembresiaDTO {

    private Long idMembresia;
    private String nombre;
    private String tipo;
    private Double precio;
    private String descripcion;
    private Integer cantidadDias; // nuevo


    public MembresiaDTO(Long idMembresia, String nombre, String tipo, Double precio, String descripcion, Integer cantidadDias) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidadDias = cantidadDias;
    }


    public MembresiaDTO(String nombre, String tipo, Double precio, String descripcion, Integer cantidadDias) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidadDias = cantidadDias;
    }


}
