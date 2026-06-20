package medilink.notificacion.service;

import lombok.AllArgsConstructor;
import medilink.notificacion.client.CitaClient;
import medilink.notificacion.dto.request.NotificacionRequest;
import medilink.notificacion.dto.response.CitaResponse;
import medilink.notificacion.dto.response.NotificacionResponse;
import medilink.notificacion.exception.NotificacionNoEncontrada;
import medilink.notificacion.mapper.NotificacionMapper;
import medilink.notificacion.model.entity.Notificacion;
import medilink.notificacion.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final NotificacionMapper notificacionMapper;
    private final CitaClient citaClient;

    public List<NotificacionResponse> obtenerTodasLasNotificaciones() {

        return notificacionRepository.findAll()
                .stream()
                .map(notificacionMapper::toResponse)
                .toList();
    }

    public NotificacionResponse obtenerNotificacionPorId(Long idNotificacion) {

        Notificacion notificacion = notificacionRepository
                .findById(idNotificacion)
                .orElseThrow(() ->
                        new NotificacionNoEncontrada(idNotificacion));

        return notificacionMapper.toResponse(notificacion);
    }

    public NotificacionResponse crearNotificacion(
            NotificacionRequest request) {

        // Consumo al microservicio Cita:
        // valida que la cita exista antes de crear la notificación.
        CitaResponse cita = citaClient.obtenerCitaPorId(request.getIdCita());

        if (cita == null || cita.getIdCita() == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        Notificacion notificacion =
                notificacionMapper.toEntity(request);

        return notificacionMapper.toResponse(
                notificacionRepository.save(notificacion)
        );
    }

    public NotificacionResponse actualizarNotificacion(
            Long idNotificacion,
            NotificacionRequest request) {

        Notificacion notificacion = notificacionRepository
                .findById(idNotificacion)
                .orElseThrow(() ->
                        new NotificacionNoEncontrada(idNotificacion));

        // Consumo al microservicio Cita:
        // valida que la cita exista antes de actualizar la notificación.
        CitaResponse cita = citaClient.obtenerCitaPorId(request.getIdCita());

        if (cita == null || cita.getIdCita() == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        notificacion.setIdPaciente(request.getIdPaciente());
        notificacion.setIdProfesional(request.getIdProfesional());
        notificacion.setIdCita(request.getIdCita());
        notificacion.setTipoNotificacion(request.getTipoNotificacion());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setFechaEnvio(request.getFechaEnvio());
        notificacion.setEstadoNotificacion(
                request.getEstadoNotificacion());

        return notificacionMapper.toResponse(
                notificacionRepository.save(notificacion)
        );
    }

    public void eliminarNotificacion(Long idNotificacion) {

        Notificacion notificacion = notificacionRepository
                .findById(idNotificacion)
                .orElseThrow(() ->
                        new NotificacionNoEncontrada(idNotificacion));

        notificacionRepository.delete(notificacion);
    }
}