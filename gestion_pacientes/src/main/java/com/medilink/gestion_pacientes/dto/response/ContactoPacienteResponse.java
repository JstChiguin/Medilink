package com.medilink.gestion_pacientes.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactoPacienteResponse {

    private Long idContacto;
    private Long idPaciente;
    private String telefono;
    private String direccionPaciente;
    private String nombreContactoEmergencia;
    private String telefonoContactoEmergencia;
}
