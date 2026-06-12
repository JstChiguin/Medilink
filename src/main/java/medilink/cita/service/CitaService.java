package medilink.cita.service;

import lombok.AllArgsConstructor;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.exception.CitaNoEncontrada;
import medilink.cita.mapper.CitaMapper;
import medilink.cita.model.entity.Cita;
import medilink.cita.repository.CitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private static final Logger log = LoggerFactory.getLogger(CitaService.class);

    public List<CitaResponse> obtenerTodasLasCitas() {
        log.info("Obteniendo listado de citas");
        return citaMapper.toResponseList(citaRepository.findAll());
    }

    public CitaResponse obtenerCitaPorId (Long idCitaBuscada) {
        log.info("Se está obteniendo una citación con id {}", idCitaBuscada);
        return citaMapper.toResponse(citaRepository
                .findById(idCitaBuscada)
                .orElseThrow(()-> new CitaNoEncontrada(idCitaBuscada)));
    }

    public CitaResponse crearCita (CitaRequest citaRequest){
        validarReglasDeNegocio (citaRequest);
        log.info("Creando nueva cita: {}", citaRequest);
        return citaMapper.toResponse(citaRepository.save(citaMapper.toEntity(citaRequest)));
    }

    public boolean eliminarCita (Long idCitaAEliminar){
        if (!citaRepository.existsById(idCitaAEliminar)){
            log.error("No se pudo eliminar la cita. El id {} no existe", idCitaAEliminar);
            throw new CitaNoEncontrada(idCitaAEliminar);
        }
        citaRepository.deleteById (idCitaAEliminar);
        log.info("Cita con id {} eliminada correctamente", idCitaAEliminar);
        return true;
    }

    public CitaResponse actualizarCita(Long idCitaAActualizar, CitaRequest citaRequest){
            Cita citaActualizada = citaRepository
                    .findById(idCitaAActualizar)
                    .orElseThrow(() -> {
                        log.error("No se encontró la cita con id {}", idCitaAActualizar);
                        return new CitaNoEncontrada(idCitaAActualizar);
                    });

            citaActualizada.setEstadoCita(citaRequest.getEstadoCita());
            citaActualizada.setObservacionesCita(citaRequest.getObservacionesCita());
            log.info("Cita con id {} actualizada correctamente", idCitaAActualizar);
            return citaMapper.toResponse(citaRepository.save(citaActualizada));
    }

    private void validarReglasDeNegocio(CitaRequest citaRequest){
        if (citaRequest.getFechaCita().getDayOfWeek()== DayOfWeek.SUNDAY){
            log.error("Intento de agendar cita en domingo");
            throw new IllegalArgumentException("No se pueden agendar citas los domingos");
        }

        if (citaRequest.getHoraCita().isBefore(LocalTime.of(8,0))
        || citaRequest.getHoraCita().isAfter(LocalTime.of(17,0))){
            log.error("Hora fuera de horario laboral: {}", citaRequest.getHoraCita());
            throw new IllegalArgumentException("La cita debe estar dentro del horario de atención (08:00 a 17:00)");
        }

        if (citaRepository.existsByFechaCitaAndHoraCita(
                citaRequest.getFechaCita(), citaRequest.getHoraCita())){
            log.error("Ya existe una cita para la fecha {} y hora {}",
                    citaRequest.getFechaCita(), citaRequest.getHoraCita());
            throw new IllegalArgumentException("Ya existe una cita agendada en esa fecha y hora");
        }
    }
}
