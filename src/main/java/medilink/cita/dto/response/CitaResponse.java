package medilink.cita.dto.response;

import lombok.Data;
import medilink.cita.model.enums.EstadoCita;
import medilink.cita.model.enums.ModalidadCita;

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
    private ModalidadCita modalidadAtencionCita;
    private EstadoCita estadoCita;
    private String motivoCita;
    private String observacionesCita;
    private LocalDate fechaCreacionCita;
    private String diagnosticoPreliminar;

}
