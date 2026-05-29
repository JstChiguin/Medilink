package medilink.pago.model.entity;

import jakarta.persistence.*;
import lombok.*;
import medilink.pago.model.enums.EstadoPago;
import medilink.pago.model.enums.MetodoPago;

import java.time.LocalDate;

@Entity
@Table(name = "pagos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "id_cita", nullable = false)
    private Long idCita;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", length = 50, nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", length = 50, nullable = false)
    private EstadoPago estadoPago;
}