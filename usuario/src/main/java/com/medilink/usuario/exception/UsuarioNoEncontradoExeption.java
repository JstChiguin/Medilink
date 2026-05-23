package com.medilink.usuario.exception;

public class UsuarioNoEncontradoExeption extends RuntimeException {
    public UsuarioNoEncontradoExeption(Long idUsuario) {
        super("No se a encontrado un usuario en el id :" + idUsuario);
    }
}
