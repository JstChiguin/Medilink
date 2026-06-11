package medilink.recetamedica.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecetaMedicaRequest {

    @NotNull(message = "el id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "el id de la cita no puede ir vacío")
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @NotNull(message = "la fecha de emisión no puede ir vacía")
    @PastOrPresent(message = "la fecha de emisión no puede ser futura")
    private LocalDate fechaEmision;

    @NotBlank(message = "el medicamento no puede ir en blanco")
    @Size(max = 150, message = "el medicamento no puede superar los 150 caracteres")
    private String medicamento;

    @NotBlank(message = "la dosis no puede ir en blanco")
    @Size(max = 150, message = "la dosis no puede superar los 150 caracteres")
    private String dosis;

    @NotBlank(message = "la frecuencia no puede ir en blanco")
    @Size(max = 150, message = "la frecuencia no puede superar los 150 caracteres")
    private String frecuencia;

    @NotBlank(message = "la duración no puede ir en blanco")
    @Size(max = 150, message = "la duración no puede superar los 150 caracteres")
    private String duracion;

    @Size(max = 1000, message = "las indicaciones no pueden superar los 1000 caracteres")
    private String indicaciones;
}