package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AccesoClienteDTO {

    private Long idAcceso;
    private Long idCliente;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;  // nuevo campo
    private Long idUsuario;
    private String estado; // 'activo' o 'inactivo'


    public AccesoClienteDTO() {
    }


    public AccesoClienteDTO(Long idAcceso, Long idCliente, LocalDate fecha,
                            LocalTime horaEntrada, LocalTime horaSalida, Long idUsuario, String estado) {
        this.idAcceso = idAcceso;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }
}
