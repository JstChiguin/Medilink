package medilink.cita.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoCita {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    REALIZADA("Realizada");

    private final String estado;

    @JsonCreator
    public static EstadoCita obtenerEstado (String estado){
        for (EstadoCita e : values()){
            if (e.estado.equalsIgnoreCase(estado)) return e;
        }

        throw new IllegalArgumentException("Estado de la cita desconocido: " + estado);
    }

    @JsonValue
    public String getValor(){
        return estado;
    }
}
