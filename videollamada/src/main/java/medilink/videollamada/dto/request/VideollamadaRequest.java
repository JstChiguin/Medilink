package medilink.videollamada.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import medilink.videollamada.model.enums.EstadoVideollamada;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VideollamadaRequest {

    @NotNull(message = "el id de la cita no puede ir vacío")
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @NotNull(message = "el id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "la fecha programada no puede ir vacía")
    @FutureOrPresent(message = "la fecha programada no puede ser anterior a hoy")
    private LocalDate fechaProgramada;

    @NotNull(message = "la hora de inicio no puede ir vacía")
    private LocalTime horaInicio;

    private LocalTime horaTermino;

    @NotBlank(message = "el enlace de sesión no puede ir en blanco")
    @Size(max = 500, message = "el enlace de sesión no puede superar los 500 caracteres")
    private String enlaceSesion;

    @NotNull(message = "el estado de la videollamada no puede ir vacío")
    private EstadoVideollamada estadoVideollamada;
}