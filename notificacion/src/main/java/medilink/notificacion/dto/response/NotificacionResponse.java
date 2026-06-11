package medilink.notificacion.dto.response;

import lombok.Data;
import medilink.notificacion.model.enums.EstadoNotificacion;
import medilink.notificacion.model.enums.TipoNotificacion;

import java.time.LocalDateTime;

@Data
public class NotificacionResponse {

    private Long idNotificacion;

    private Long idPaciente;

    private Long idProfesional;

    private Long idCita;

    private TipoNotificacion tipoNotificacion;

    private String mensaje;

    private LocalDateTime fechaEnvio;

    private EstadoNotificacion estadoNotificacion;
}