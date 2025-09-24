package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String telefono;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String registradoPor;


    public ClienteDTO(Long id, String nombre, String apellido, String email,
                      String dni, String telefono, LocalDate fechaNacimiento,
                      LocalDate fechaRegistro, String registradoPor) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
    }


    public ClienteDTO(Long id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }


}
