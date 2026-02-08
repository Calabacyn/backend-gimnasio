package com.backend_gimnasio.backend_gimnasio.enums;

public enum PaymentMethodEnum {
    CASH("Efectivo"),
    TRANSFER("Transferencia"),
    CARD("Tarjeta"),
    MP("Mercado Pago");

    private final String nombre;

    PaymentMethodEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
