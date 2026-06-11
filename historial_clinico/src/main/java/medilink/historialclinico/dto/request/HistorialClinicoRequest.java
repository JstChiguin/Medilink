package medilink.historialclinico.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistorialClinicoRequest {

    @NotNull(message = "el id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "el id de la cita no puede ir vacío")
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @NotNull(message = "la fecha de atención no puede ir vacía")
    @PastOrPresent(message = "la fecha de atención no puede ser futura")
    private LocalDate fechaAtencion;

    @NotBlank(message = "el diagnóstico no puede ir en blanco")
    @Size(max = 500, message = "el diagnóstico no puede superar los 500 caracteres")
    private String diagnostico;

    @Size(max = 1000, message = "las observaciones no pueden superar los 1000 caracteres")
    private String observaciones;

    @Size(max = 1000, message = "las recomendaciones no pueden superar los 1000 caracteres")
    private String recomendaciones;
}