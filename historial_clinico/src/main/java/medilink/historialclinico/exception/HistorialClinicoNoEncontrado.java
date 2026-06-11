package medilink.historialclinico.exception;

public class HistorialClinicoNoEncontrado extends RuntimeException {

    public HistorialClinicoNoEncontrado(Long idHistorial) {
        super("No existe un historial clínico con el id: " + idHistorial);
    }
}