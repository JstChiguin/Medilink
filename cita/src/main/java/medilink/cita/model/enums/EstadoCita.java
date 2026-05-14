package medilink.cita.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EstadoCita {
    Pendiente("Pendiente"),
    Confirmada("Confirmada"),
    Cancelada("Cancelada"),
    Realizada("Realizada");

    private final String valor;

    public static EstadoCita fromValor(String valor){
        for (EstadoCita r : values()){
            if (r.valor.equals(valor)) return r;
        }

        throw new IllegalArgumentException("Estado de la cita desconocida: " + valor);
    }
}
