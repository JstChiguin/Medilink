package medilink.cita.service;

import medilink.cita.model.Cita;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CitaService {

   ArrayList<Cita> listaCita = new ArrayList<>(); //creo esto no va en bd

    public  ArrayList<Cita> listar() {
        return listaCita;
    }

    public Cita crear (Cita cita){
        listaCita.add(cita);
        return cita;
    }

    public boolean eliminar(Long idCita){
        return listaCita.removeIf( aux -> aux.getIdCita().equals(idCita));
    }

    public Cita actualizar(Long idCita, Cita cita){
        for(Cita citaAux : listaCita) {
            if (citaAux.getIdCita().equals(idCita)){
                citaAux.getIdPaciente().equals(cita.getIdPaciente());
                citaAux.getIdProfesional().equals(cita.getIdProfesional());
                citaAux.getIdAgenda().equals(cita.getIdAgenda());
                citaAux.setFechaCita(cita.getFechaCita());
                citaAux.setHoraCita(cita.getHoraCita());



            }
        }
    }
}


//    private String modalidadAtencionCita;
//    private String estadoCita;
//    private String motivoCita;
//    private String observacionesCita;
//    private Date fechaCreacionCita;
//}