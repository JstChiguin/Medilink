package medilink.agenda.exception;

public class AgendaNoEncontrada extends RuntimeException {

    public AgendaNoEncontrada(Long idAgenda) {
        super("No existe una agenda médica con el id: " + idAgenda);
    }

}