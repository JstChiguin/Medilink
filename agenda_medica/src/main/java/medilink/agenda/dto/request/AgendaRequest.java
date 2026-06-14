package medilink.agenda.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import medilink.agenda.model.enums.EstadoAgenda;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendaRequest {

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "la fecha no puede ir vacía")
    @FutureOrPresent(message = "la fecha no puede ser anterior a hoy")
    private LocalDate fecha;

    @NotNull(message = "la hora de inicio no puede ir vacía")
    private LocalTime horaInicio;

    @NotNull(message = "la hora de término no puede ir vacía")
    private LocalTime horaFin;

    @NotNull(message = "el estado de la agenda no puede ir vacío")
    private EstadoAgenda estado;

    @Size(max = 200, message = "la observación no puede superar los 200 caracteres")
    private String observacion;
}