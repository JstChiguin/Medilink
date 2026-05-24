package com.medilink.usuario.exception;

public class CorreoDuplicadoException extends RuntimeException {

    public CorreoDuplicadoException(String correoUsuario) {
        super("El correo " + correoUsuario + " ya está registrado");
    }
}
