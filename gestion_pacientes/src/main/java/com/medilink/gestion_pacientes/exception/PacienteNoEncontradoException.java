package com.medilink.gestion_pacientes.exception;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long idPaciente) {
        super("No se ha encontrado un paciente con el id: " + idPaciente);
    }
}
