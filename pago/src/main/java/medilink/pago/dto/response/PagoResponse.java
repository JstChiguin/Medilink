package medilink.pago.dto.response;

import lombok.Data;
import medilink.pago.model.enums.EstadoPago;
import medilink.pago.model.enums.MetodoPago;

import java.time.LocalDate;

@Data
public class PagoResponse {

    private Long idPago;
    private Long idPaciente;
    private Long idProfesional;
    private Long idCita;
    private LocalDate fechaPago;
    private Double monto;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;
}