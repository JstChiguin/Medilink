package medilink.cita.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import medilink.cita.CitaApplication;
import medilink.cita.exception.NoHayRegistro;
import medilink.cita.model.Cita;
import medilink.cita.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    public List<Cita> obtenerTodasLasCitas(){
        return citaRepository.findAll();
    }

    public Cita obtenerUnaCita (Long idCita){
        return citaRepository.findByIdCita(idCita).orElseThrow(() -> new NoHayRegistro(idCita));
    }

    public Cita crearCita(Cita citaACrear){
        return citaRepository.save(citaACrear);
    }

    public  boolean eliminarCita(Long idCita){
        try(
                citaRepository.delateById(idCita);
                return false
                )
    } ////Falta

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
                citaAux.setModalidadAtencionCita(cita.getModalidadAtencionCita());
                citaAux.setEstadoCita(cita.getEstadoCita());
                citaAux.setMotivoCita(cita.getMotivoCita());
                citaAux.setObservacionesCita(cita.getObservacionesCita());
                citaAux.setFechaCreacionCita(cita.getFechaCreacionCita());

                return citaAux;
            }
        }
            return null;
    }

}



//    private String estadoCita;
//    private String motivoCita;
//    private String observacionesCita;
//    private Date fechaCreacionCita;
//}