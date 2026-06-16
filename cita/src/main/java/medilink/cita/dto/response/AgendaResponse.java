package medilink.cita.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaResponse {

    private Long idAgenda;
    private Long idProfesional;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private String observacion;
}