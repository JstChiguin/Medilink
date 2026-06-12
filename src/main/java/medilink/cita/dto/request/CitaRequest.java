package medilink.cita.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import medilink.cita.model.enums.EstadoCita;
import medilink.cita.model.enums.ModalidadCita;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {

    @NotNull(message = "el Id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el Id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull (message = "el Id de la agenda no puede ir vacío")
    @Min(value = 1, message = "el id de la agenda no puede ser menor a 1")
    private Long idAgenda;

    @NotNull ( message = "la cita debe registrar fecha")
    @FutureOrPresent ( message = "la fecha de la cita no puede ser anterior a hoy")
    private LocalDate fechaCita;

    @NotNull( message = "la cita debe registrar hora")
    private LocalTime horaCita;

    @NotNull(message = "la modalidad de atención no puede ir vacía")
    private ModalidadCita modalidadAtencionCita;

    @NotNull (message = "el estado de la cita no puede ir vacío")
    private EstadoCita estadoCita;

    @NotBlank (message = "el motivo de la cita no puede ir en blanco")
    @Size(max=100, message = "el motivo de la cita no puede superar los 100 caracteres")
    private String motivoCita;

    @Size(max=200, message = "las observaciones de la cita no pueden superar los 200 caracteres")
    private String observacionesCita;

    @NotNull (message = "la fecha de la creación de la cita no puede ir vacía")
    @PastOrPresent (message = "la fecha de creación de la cita no puede ser futura")
    private LocalDate fechaCreacionCita;

}
