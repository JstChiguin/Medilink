package com.medilink.gestion_pacientes.exception;

public class PerfilNoEncontradoException extends RuntimeException {
    public PerfilNoEncontradoException(Long idPaciente) {
        super("No se ha encontrado un perfil para el paciente con id: " + idPaciente);
    }
}
