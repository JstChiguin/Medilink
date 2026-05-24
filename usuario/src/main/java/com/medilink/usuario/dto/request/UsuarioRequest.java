package com.medilink.usuario.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150,
            message = "El nombre no puede superar los 150 caracteres")
    private String nombreUsuario;

    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "El correo debe tener un formato válido"
    )
    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 150,
            message = "El correo no puede superar los 150 caracteres")
    private String correoUsuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 50,
            message = "La contraseña debe tener entre 8 y 50 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$",
            message = "La contraseña debe contener mayúscula, minúscula y número"
    )
    private String contrasennaUsuario;

    @NotBlank(message = "El rol es obligatorio")
    private String rolUsuario;

    @NotBlank(message = "El estado es obligatorio")
    private String estadoUsuario;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistroUsuario;
}