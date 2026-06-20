package medilink.agenda.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoAgenda {

    DISPONIBLE("Disponible"),
    RESERVADA("Reservada"),
    BLOQUEADA("Bloqueada");

    private final String estado;

    @JsonCreator
    public static EstadoAgenda obtenerEstado(String estado){
        for (EstadoAgenda e : values()){
            if (e.estado.equalsIgnoreCase(estado)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Estado de agenda desconocido: " + estado);
    }

    @JsonValue
    public String getValor(){
        return estado;
    }
}