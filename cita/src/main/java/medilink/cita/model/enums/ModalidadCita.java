package medilink.cita.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModalidadCita {

    Presencial("Presencial"),
    Remota("Remota");

    private final String valor;

    public static ModalidadCita fromValor(String valor){
        for (ModalidadCita r : values()){
            if (r.valor.equals(valor)) return r;
        }

        throw new IllegalArgumentException("Modalidad de la cita desconocida: " + valor);
    }
}
