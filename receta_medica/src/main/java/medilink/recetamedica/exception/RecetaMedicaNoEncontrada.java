package medilink.recetamedica.exception;

public class RecetaMedicaNoEncontrada extends RuntimeException {

    public RecetaMedicaNoEncontrada(Long idReceta) {
        super("No existe una receta médica con el id: " + idReceta);
    }
}