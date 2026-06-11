package medilink.pago.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import medilink.pago.model.enums.EstadoPago;
import medilink.pago.model.enums.MetodoPago;

import java.time.LocalDate;

@Data
public class PagoRequest {

    @NotNull(message = "el id del paciente no puede ir vacío")
    @Min(value = 1, message = "el id del paciente no puede ser menor a 1")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    @Min(value = 1, message = "el id del profesional no puede ser menor a 1")
    private Long idProfesional;

    @NotNull(message = "el id de la cita no puede ir vacío")
    @Min(value = 1, message = "el id de la cita no puede ser menor a 1")
    private Long idCita;

    @NotNull(message = "la fecha de pago no puede ir vacía")
    @PastOrPresent(message = "la fecha de pago no puede ser futura")
    private LocalDate fechaPago;

    @NotNull(message = "el monto no puede ir vacío")
    @Positive(message = "el monto debe ser mayor a cero")
    private Double monto;

    @NotNull(message = "el método de pago no puede ir vacío")
    private MetodoPago metodoPago;

    @NotNull(message = "el estado del pago no puede ir vacío")
    private EstadoPago estadoPago;
}