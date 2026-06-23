package com.medilink.usuario.dto.response;

import com.medilink.usuario.modelo.enums.EstadoUsuario;
import com.medilink.usuario.modelo.enums.RolUsuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResponse {

    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String contrasennaUsuario;
    private RolUsuario rolUsuario;
    private EstadoUsuario estadoUsuario;
    private LocalDate fechaRegistroUsuario;

}
