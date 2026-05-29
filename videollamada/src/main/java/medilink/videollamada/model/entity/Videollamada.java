package medilink.videollamada.model.entity;

import jakarta.persistence.*;
import lombok.*;
import medilink.videollamada.model.enums.EstadoVideollamada;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "videollamadas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Videollamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videollamada")
    private Long idVideollamada;

    @Column(name = "id_cita", nullable = false)
    private Long idCita;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_termino")
    private LocalTime horaTermino;

    @Column(name = "enlace_sesion", length = 500, nullable = false)
    private String enlaceSesion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_videollamada", length = 50, nullable = false)
    private EstadoVideollamada estadoVideollamada;
}