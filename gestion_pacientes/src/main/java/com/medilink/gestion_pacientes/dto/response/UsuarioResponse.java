package com.medilink.gestion_pacientes.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

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
