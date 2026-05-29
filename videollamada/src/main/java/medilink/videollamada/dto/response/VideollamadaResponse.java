package medilink.videollamada.dto.response;

import lombok.Data;
import medilink.videollamada.model.enums.EstadoVideollamada;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VideollamadaResponse {

    private Long idVideollamada;
    private Long idCita;
    private Long idPaciente;
    private Long idProfesional;
    private LocalDate fechaProgramada;
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private String enlaceSesion;
    private EstadoVideollamada estadoVideollamada;
}