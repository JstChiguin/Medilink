package com.medilink.gestion_pacientes.exception;

public class ContactoNoEncontradoException extends RuntimeException {
    public ContactoNoEncontradoException(Long idPaciente) {
        super("No se ha encontrado un contacto para el paciente con id: " + idPaciente);
    }
}
