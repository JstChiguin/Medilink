package medilink.cita.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.exception.CitaNoEncontrada;
import medilink.cita.model.entity.Cita;
import medilink.cita.repository.CitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class CitaService {

    private final CitaRepository citaRepository;

    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    public Cita obtenerCitaPorId (Long idCitaBuscada){
        return citaRepository.findById(idCitaBuscada).orElseThrow(()-> new CitaNoEncontrada(idCitaBuscada));
    }

    public Cita crearCita (@Valid @RequestBody CitaRequest citaRequest){
        Cita citaACrear = new Cita();
        citaACrear.setFechaCita(citaRequest.getFechaCita());
        citaACrear.setHoraCita(citaRequest.getHoraCita());
        return citaRepository.save(citaACrear);
    } //me falta mas info de que ingresa

    public boolean eliminarCita (Long idCitaAEliminar){
        try {
            citaRepository.deleteById (idCitaAEliminar);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Cita actualizarCita(@Valid @PathVariable Long idCitaAActualizar, @RequestBody CitaRequest citaRequest){
        Cita citaActualizada = citaRepository
                .findById(idCitaAActualizar).orElseThrow(()-> new CitaNoEncontrada(idCitaAActualizar));

        citaActualizada.setEstadoCita(citaRequest.getEstadoCita());
        citaActualizada.setObservacionesCita(citaRequest.getObservacionesCita());

        return citaRepository.save(citaActualizada);
    } 

}
