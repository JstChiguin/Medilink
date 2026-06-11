package medilink.notificacion.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoNotificacion {

    EMAIL("Email"),
    SMS("SMS"),
    WHATSAPP("WhatsApp");

    private final String valor;

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static TipoNotificacion fromValor(String valor) {

        for (TipoNotificacion t : values()) {
            if (t.valor.equalsIgnoreCase(valor)) {
                return t;
            }
        }

        throw new IllegalArgumentException(
                "Tipo de notificación desconocido: " + valor
        );
    }
}