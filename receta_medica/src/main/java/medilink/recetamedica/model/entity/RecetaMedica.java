package medilink.recetamedica.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "recetas_medicas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "id_cita", nullable = false)
    private Long idCita;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "medicamento", length = 150, nullable = false)
    private String medicamento;

    @Column(name = "dosis", length = 150, nullable = false)
    private String dosis;

    @Column(name = "frecuencia", length = 150, nullable = false)
    private String frecuencia;

    @Column(name = "duracion", length = 150, nullable = false)
    private String duracion;

    @Column(name = "indicaciones", length = 1000)
    private String indicaciones;
}