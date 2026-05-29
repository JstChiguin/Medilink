package medilink.recetamedica.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecetaMedicaResponse {

    private Long idReceta;
    private Long idPaciente;
    private Long idProfesional;
    private Long idCita;
    private LocalDate fechaEmision;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private String duracion;
    private String indicaciones;
}