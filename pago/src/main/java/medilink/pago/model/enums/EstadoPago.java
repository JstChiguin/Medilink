package medilink.pago.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoPago {

    PENDIENTE("Pendiente"),
    PAGADO("Pagado"),
    RECHAZADO("Rechazado");

    private final String valor;

    @JsonCreator
    public static EstadoPago fromValor(String valor) {
        for (EstadoPago e : values()) {
            if (e.valor.equalsIgnoreCase(valor)) return e;
        }

        throw new IllegalArgumentException("Estado de pago desconocido: " + valor);
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}