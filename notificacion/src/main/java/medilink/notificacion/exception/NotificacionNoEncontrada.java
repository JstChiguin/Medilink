package medilink.notificacion.exception;

public class NotificacionNoEncontrada extends RuntimeException {

    public NotificacionNoEncontrada(Long idNotificacion) {
        super("No existe una notificación con el id: " + idNotificacion);
    }
}