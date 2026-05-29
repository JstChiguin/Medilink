package medilink.videollamada.exception;

public class VideollamadaNoEncontrada extends RuntimeException {

    public VideollamadaNoEncontrada(Long idVideollamada) {
        super("No existe una videollamada con el id: " + idVideollamada);
    }
}