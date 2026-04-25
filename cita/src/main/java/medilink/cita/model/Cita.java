package medilink.cita.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cita {
    private Long idCita; //PK
    private Long idPaciente; //FK
    private Long idProfesional; //FK
    private Long idAgenda; //FK
    private Date fechaCita;
    private Time horaCita;
    private String modalidadAtencionCita;
    private String estadoCita;
    private String motivoCita;
    private String observacionesCita;

    //
    private Date fechaCreacionCita;
}
