package medilink.cita.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModalidadCita {

    PRESENCIAL("Presencial"),
    REMOTA("Remota");

    private final String valor;

    @JsonCreator
    public static ModalidadCita fromValor(String valor){
        for (ModalidadCita r : values()){
            if (r.valor.equalsIgnoreCase(valor)) return r;
        }

        throw new IllegalArgumentException("Modalidad de la cita desconocida: " + valor);
    }

    @JsonValue
    public String getValor(){
        return valor;
    }
}
