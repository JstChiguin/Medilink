package com.medilink.gestion_pacientes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactoPacienteRequest {

    @NotNull(message = "El id del paciente es obligatorio")
    private Long idPaciente;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    private String direccionPaciente;

    @NotBlank(message = "El nombre del contacto de emergencia es obligatorio")
    @Size(max = 100, message = "El nombre del contacto de emergencia no puede superar los 100 caracteres")
    private String nombreContactoEmergencia;

    @NotBlank(message = "El teléfono del contacto de emergencia es obligatorio")
    @Size(max = 20, message = "El teléfono del contacto de emergencia no puede superar los 20 caracteres")
    private String telefonoContactoEmergencia;

}
