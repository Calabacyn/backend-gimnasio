package com.backend_gimnasio.backend_gimnasio.enums;

public enum InvoiceStatusEnum {
    PENDING("Pendiente"),
    PAID("Pagada"),
    CANCELLED("Cancelada");

    private final String nombre;

    InvoiceStatusEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
