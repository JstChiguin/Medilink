package medilink.evaluacionatencion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluacionAtencionRequest {

    @NotNull(message = "el id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "el id de la cita no puede ir vacío")
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @NotNull(message = "la fecha de evaluación no puede ir vacía")
    @PastOrPresent(message = "la fecha de evaluación no puede ser futura")
    private LocalDate fechaEvaluacion;

    @NotNull(message = "la calificación no puede ir vacía")
    @Min(value = 1, message = "la calificación mínima es 1")
    @Max(value = 5, message = "la calificación máxima es 5")
    private Integer calificacion;

    @Size(max = 1000, message = "el comentario no puede superar los 1000 caracteres")
    private String comentario;
}