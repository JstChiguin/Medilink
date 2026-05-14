package medilink.cita.model.entity;
///VAL ENTRADA Y MANEJO DE ERRORES
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table (name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder ///instancia automaticamente
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita; //PK

    //@ManyToOne
    @Column(name = "paciente", nullable = false)
    private Long idPaciente; //FK

    //@ManyToOne
    private Long idProfesional; //FK

    //@ManyToOne
    private Long idAgenda; //FK

    @Column(name = "fecha citación", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora citación", nullable = false)
    private LocalTime horaCita;

    @Column(name = "modalidad atención", length = 50, nullable = false)
    //@OneToOne
    private String modalidadAtencionCita;

    @Column(name = "estado", length = 50, nullable = false)
    //@OneToOne
    private String estadoCita;

    @Column(name = "motivo", length = 50, nullable = false)
    private String motivoCita;

    @Column(name = "observaciones", length = 100, nullable = true)
    private String observacionesCita;

    @Column(name = "fecha creación", nullable = false)
    private LocalDate fechaCreacionCita;

}
