package com.medilink.gestion_pacientes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioResponse {

    private Long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String contrasennaUsuario;
    private String rolUsuario;
    private String estadoUsuario;
    private LocalDate fechaRegistroUsuario;
}
