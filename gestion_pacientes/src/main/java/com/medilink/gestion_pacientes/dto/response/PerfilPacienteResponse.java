package com.medilink.gestion_pacientes.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilPacienteResponse {

    private Long idPerfil;
    private PacienteResponse paciente;
    private ContactoPacienteResponse contacto;
    private String antecedentesMedicos;
    private String alergias;
    private String medicamentosActuales;
    private String informacionRelevante;
    private LocalDateTime ultimaActualizacion;
}
