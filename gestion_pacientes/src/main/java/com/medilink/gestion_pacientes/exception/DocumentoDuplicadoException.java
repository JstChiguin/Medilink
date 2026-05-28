package com.medilink.gestion_pacientes.exception;

public class DocumentoDuplicadoException extends RuntimeException {
    public DocumentoDuplicadoException(String numeroDocumento) {
        super("El número de documento " + numeroDocumento + " ya está registrado");
    }
}
