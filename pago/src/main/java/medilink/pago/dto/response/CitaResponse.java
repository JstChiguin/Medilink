package medilink.pago.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaResponse {

    private Long idCita;
    private Long idPaciente;
    private Long idProfesional;
    private LocalDate fechaCitacion;
    private LocalTime horaCitacion;
    private String estado;
    private String modalidadAtencion;
    private String motivo;
    private String observaciones;
}