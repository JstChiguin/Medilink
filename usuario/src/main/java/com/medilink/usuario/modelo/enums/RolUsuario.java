package com.medilink.usuario.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolUsuario {
    Medico,
    Paciente,
    Administrador;

    public static RolUsuario fromValor(String valor) {

        for (RolUsuario rol : values()) {

            if (rol.name().equalsIgnoreCase(valor.trim())) {
                return rol;
            }
        }

        throw new IllegalArgumentException(
                "Rol inválido: " + valor
        );
    }

}
