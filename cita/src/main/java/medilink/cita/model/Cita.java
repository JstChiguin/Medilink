package medilink.cita.model;
///VAL ENTRADA Y MANEJO DE ERRORES
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Time;



@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder ///instancia automaticamente

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita; //PK

    @ManyToOne
    @Column(name = "paciente", nullable = false)
    private Paciente paciente; //FK

    @ManyToOne
    private Profesional profesional; //FK

    @ManyToOne
    private Agenda agenda; //FK

    @Column()
    private Date fechaCita;

    @Column()
    private Time horaCita;

    @Column()
    private String modalidadAtencionCita;

    @Column()
    private String estadoCita;

    @Column()
    private String motivoCita;

    @Column()
    private String observacionesCita;

    @Column()
    private Date fechaCreacionCita; ///timetamp

    //@OneToMany
    //private ArrayList<Direccion> direcciones;
}
