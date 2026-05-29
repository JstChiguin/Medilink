package medilink.evaluacionatencion.exception;

public class EvaluacionAtencionNoEncontrada extends RuntimeException {

    public EvaluacionAtencionNoEncontrada(Long idEvaluacion) {
        super("No existe una evaluación de atención con el id: " + idEvaluacion);
    }
}