package medilink.pago.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MetodoPago {

    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    TRANSFERENCIA("Transferencia");

    private final String valor;

    @JsonCreator
    public static MetodoPago fromValor(String valor) {
        for (MetodoPago m : values()) {
            if (m.valor.equalsIgnoreCase(valor)) return m;
        }

        throw new IllegalArgumentException("Método de pago desconocido: " + valor);
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}