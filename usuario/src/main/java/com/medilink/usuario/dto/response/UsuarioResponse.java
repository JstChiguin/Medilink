package com.medilink.usuario.dto.response;

import com.medilink.usuario.modelo.EstadoUsuario;
import com.medilink.usuario.modelo.RolUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
