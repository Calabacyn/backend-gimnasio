package com.backend_gimnasio.backend_gimnasio.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("Administrador"),
    TRAINER("Entrenador"),
    CLIENT("Cliente"),
    STAFF("Personal");

    private final String displayName;

    RoleEnum(String displayName) {
        this.displayName = displayName;
    }
}
