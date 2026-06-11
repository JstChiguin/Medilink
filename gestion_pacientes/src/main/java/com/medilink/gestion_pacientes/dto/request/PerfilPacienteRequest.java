package com.medilink.gestion_pacientes.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilPacienteRequest {

    private String antecedentesMedicos;

    private String alergias;

    private String medicamentosActuales;

    private String informacionRelevante;

}
