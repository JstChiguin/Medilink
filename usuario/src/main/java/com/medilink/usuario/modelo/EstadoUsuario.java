package com.medilink.usuario.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoUsuario {
    Activo,
    Inactivo,
    Bloqueado;
}
