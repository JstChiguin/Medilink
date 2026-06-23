package com.medilink.usuario.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoUsuario {
    Activo,
    Inactivo,
    Bloqueado;

    public static EstadoUsuario fromValor(String valor) {

        for (EstadoUsuario estado : values()) {

            if (estado.name().equalsIgnoreCase(valor.trim())) {
                return estado;
            }
        }

        throw new IllegalArgumentException(
                "Estado inválido: " + valor
        );
    }


}
