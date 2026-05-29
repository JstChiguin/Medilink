package medilink.notificacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import medilink.notificacion.model.enums.EstadoNotificacion;
import medilink.notificacion.model.enums.TipoNotificacion;

import java.time.LocalDateTime;

@Data
public class NotificacionRequest {

    @NotNull(message = "el id del paciente no puede ir vacío")
    private Long idPaciente;

    @NotNull(message = "el id del profesional no puede ir vacío")
    private Long idProfesional;

    @NotNull(message = "el id de la cita no puede ir vacío")
    private Long idCita;

    @NotNull(message = "el tipo de notificación no puede ir vacío")
    private TipoNotificacion tipoNotificacion;

    @NotBlank(message = "el mensaje no puede ir vacío")
    private String mensaje;

    @NotNull(message = "la fecha de envío no puede ir vacía")
    private LocalDateTime fechaEnvio;

    @NotNull(message = "el estado de la notificación no puede ir vacío")
    private EstadoNotificacion estadoNotificacion;
}