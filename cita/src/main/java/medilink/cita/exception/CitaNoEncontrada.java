package medilink.cita.exception;

public class CitaNoEncontrada extends RuntimeException {
    public CitaNoEncontrada(Long idCita) {
        super("No existe una cita con el id: "+ idCita);
    }
}
