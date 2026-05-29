package medilink.historialclinico.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "historial_clinico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long idHistorial;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "id_cita", nullable = false)
    private Long idCita;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDate fechaAtencion;

    @Column(name = "diagnostico", length = 500, nullable = false)
    private String diagnostico;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @Column(name = "recomendaciones", length = 1000)
    private String recomendaciones;
}