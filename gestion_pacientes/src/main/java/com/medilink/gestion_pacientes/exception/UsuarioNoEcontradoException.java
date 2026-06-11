package com.medilink.gestion_pacientes.exception;

public class UsuarioNoEcontradoException extends RuntimeException {
    public UsuarioNoEcontradoException(Long idUsuario) {
        super("No se ha encontrado el usuario: " + idUsuario);
    }
    public UsuarioNoEcontradoException(String message){
        super(message);
    }
}
