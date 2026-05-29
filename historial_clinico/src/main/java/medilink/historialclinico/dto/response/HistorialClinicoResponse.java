package medilink.historialclinico.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistorialClinicoResponse {

    private Long idHistorial;
    private Long idPaciente;
    private Long idProfesional;
    private Long idCita;
    private LocalDate fechaAtencion;
    private String diagnostico;
    private String observaciones;
    private String recomendaciones;
}