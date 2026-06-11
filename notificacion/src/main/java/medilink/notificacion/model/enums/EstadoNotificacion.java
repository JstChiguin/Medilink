package medilink.notificacion.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoNotificacion {

    PENDIENTE("Pendiente"),
    ENVIADA("Enviada"),
    FALLIDA("Fallida");

    private final String valor;

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static EstadoNotificacion fromValor(String valor) {

        for (EstadoNotificacion e : values()) {
            if (e.valor.equalsIgnoreCase(valor)) {
                return e;
            }
        }

        throw new IllegalArgumentException(
                "Estado de notificación desconocido: " + valor
        );
    }
}