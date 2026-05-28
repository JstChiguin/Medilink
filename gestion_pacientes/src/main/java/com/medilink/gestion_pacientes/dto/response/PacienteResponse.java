package com.medilink.gestion_pacientes.dto.response;



import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponse {

    private Long idPaciente;
    private Long idUsuario;
    private String numeroDocumento;
    private String nombreCompletoPaciente;
    private LocalDate fechaNacimiento;
    private String genero;
    private Boolean estadoActivo;
    private LocalDateTime fechaRegistro;
    private UsuarioLimitado usuario;
}
