package medilink.cita.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import medilink.cita.model.enums.EstadoCita;
import medilink.cita.model.enums.ModalidadCita;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table (name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "id_agenda", nullable = false)
    private Long idAgenda;

    @Column(name = "fecha_citacion", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_citacion", nullable = false)
    private LocalTime horaCita;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad_atencion", length = 50, nullable = false)
    private ModalidadCita modalidadAtencionCita;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 50, nullable = false)
    private EstadoCita estadoCita;

    @Column(name = "motivo", length = 100, nullable = false)
    private String motivoCita;

    @Column(name = "observaciones", length = 200)
    private String observacionesCita;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacionCita;

}
