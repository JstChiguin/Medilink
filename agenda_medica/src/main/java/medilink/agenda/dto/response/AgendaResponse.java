package medilink.agenda.dto.response;

import lombok.Data;
import medilink.agenda.model.enums.EstadoAgenda;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaResponse {

    private Long idAgenda;
    private Long idProfesional;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoAgenda estado;
    private String observacion;

}