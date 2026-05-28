package com.medilink.gestion_pacientes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteRequest {

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 20, message = "El número de documento no puede superar los 20 caracteres")
    private String numeroDocumento;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombreCompletoPaciente;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estadoActivo;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDateTime fechaRegistro;
}
