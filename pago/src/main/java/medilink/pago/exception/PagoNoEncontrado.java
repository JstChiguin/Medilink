package medilink.pago.exception;

public class PagoNoEncontrado extends RuntimeException {

    public PagoNoEncontrado(Long idPago) {
        super("No existe un pago con el id: " + idPago);
    }
}