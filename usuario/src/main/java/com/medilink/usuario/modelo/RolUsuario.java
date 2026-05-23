package com.medilink.usuario.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolUsuario {

    // Roles disponibles en el sistema
    Medico,
    Paciente,
    Administrador;

}
