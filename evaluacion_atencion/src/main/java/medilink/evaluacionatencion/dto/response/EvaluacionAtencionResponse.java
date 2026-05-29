package medilink.evaluacionatencion.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluacionAtencionResponse {

    private Long idEvaluacion;
    private Long idPaciente;
    private Long idProfesional;
    private Long idCita;
    private LocalDate fechaEvaluacion;
    private Integer calificacion;
    private String comentario;
}