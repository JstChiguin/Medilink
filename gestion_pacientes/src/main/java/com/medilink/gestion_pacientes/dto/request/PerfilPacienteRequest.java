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

    @NotNull(message = "El id del paciente es obligatorio")
    private Long idPaciente;

    @NotNull(message = "El id del contacto es obligatorio")
    private Long idContacto;

    private String antecedentesMedicos;

    private String alergias;

    private String medicamentosActuales;

    private String informacionRelevante;

    @NotNull(message = "La fecha de última actualización es obligatoria")
    private LocalDateTime ultimaActualizacion;
}
