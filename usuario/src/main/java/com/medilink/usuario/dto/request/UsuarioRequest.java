package com.medilink.usuario.dto.request;


import com.medilink.usuario.modelo.EstadoUsuario;
import com.medilink.usuario.modelo.RolUsuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotBlank ( message = "El nombre es obligatorio" )
    @Size(min = 1, max = 150, message = "El nombre es obligatorio")
    private String nombreUsuario;

    @NotBlank( message = "El correo es obligatorio" )
    @Size(min = 1, max = 150, message = "El nombre es obligatorio")
    private String correoUsuario;

    @NotBlank( message = "La contraseña es obligatoria" )
    @Size(min = 1, max = 50, message = "El nombre es obligatorio")
    private String contrasennaUsuario;


    private RolUsuario rolUsuario;

    private EstadoUsuario estadoUsuario;

    private LocalDate fechaRegistroUsuario;
}
