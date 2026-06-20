package medilink.evaluacionatencion.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaResponse {

    private Long idCita;
    private Long idPaciente;
    private Long idProfesional;
    private Long idAgenda;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String modalidadAtencionCita;
    private String estadoCita;
    private String motivoCita;
    private String observacionesCita;
}