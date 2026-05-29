package medilink.videollamada.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoVideollamada {

    PROGRAMADA("Programada"),
    EN_CURSO("En curso"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String valor;

    @JsonCreator
    public static EstadoVideollamada fromValor(String valor) {
        for (EstadoVideollamada e : values()) {
            if (e.valor.equalsIgnoreCase(valor)) return e;
        }

        throw new IllegalArgumentException("Estado de videollamada desconocido: " + valor);
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}