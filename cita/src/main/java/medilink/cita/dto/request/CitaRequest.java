package medilink.cita.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @Min(value = 1, message = "el id de la agenda no puede ser menor a 1")
    private Long idAgenda;

    @NotNull ( message = "la cita debe registrar fecha")
    private LocalDate fechaCita;

    @NotNull( message = "la cita debe registrar hora")
    private LocalTime horaCita;

    @NotBlank(message = "la modalidad de atencion no puede ir en blanco")
    private String modalidadAtencionCita;

    @NotBlank (message = "el estado de la cita no puede ir en blanco")
    private String estadoCita;

    @NotBlank (message = "el motivo de la cita no puede ir en blanco")
    private String motivoCita;

    @NotBlank (message = "las observaciones de la cita no puede ir en blanco")
    private String observacionesCita;

    @NotBlank (message = "la fecha de la cita no puede ir en blanco")
    private LocalDate fechaCreacionCita;

}
