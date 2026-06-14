package medilink.agenda.service;

import lombok.AllArgsConstructor;
import medilink.agenda.dto.request.AgendaRequest;
import medilink.agenda.dto.response.AgendaResponse;
import medilink.agenda.exception.AgendaNoEncontrada;
import medilink.agenda.mapper.AgendaMapper;
import medilink.agenda.model.entity.AgendaMedica;
import medilink.agenda.repository.AgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    private static final Logger log = LoggerFactory.getLogger(AgendaService.class);

    public List<AgendaResponse> obtenerTodasLasAgendas() {
        log.info("Obteniendo listado de agendas médicas");
        return agendaMapper.toResponseList(agendaRepository.findAll());
    }

    public AgendaResponse obtenerAgendaPorId(Long idAgenda) {
        log.info("Obteniendo agenda médica con id {}", idAgenda);
        return agendaMapper.toResponse(agendaRepository
                .findById(idAgenda)
                .orElseThrow(() -> new AgendaNoEncontrada(idAgenda)));
    }

    public AgendaResponse crearAgenda(AgendaRequest agendaRequest) {
        validarReglasDeNegocio(agendaRequest);
        log.info("Creando nueva agenda médica: {}", agendaRequest);
        return agendaMapper.toResponse(agendaRepository.save(agendaMapper.toEntity(agendaRequest)));
    }

    public boolean eliminarAgenda(Long idAgenda) {
        if (!agendaRepository.existsById(idAgenda)) {
            log.error("No se pudo eliminar la agenda médica. El id {} no existe", idAgenda);
            throw new AgendaNoEncontrada(idAgenda);
        }

        agendaRepository.deleteById(idAgenda);
        log.info("Agenda médica con id {} eliminada correctamente", idAgenda);
        return true;
    }

    public AgendaResponse actualizarAgenda(Long idAgenda, AgendaRequest agendaRequest) {
        AgendaMedica agendaActualizada = agendaRepository
                .findById(idAgenda)
                .orElseThrow(() -> {
                    log.error("No se encontró la agenda médica con id {}", idAgenda);
                    return new AgendaNoEncontrada(idAgenda);
                });

        agendaActualizada.setIdProfesional(agendaRequest.getIdProfesional());
        agendaActualizada.setFecha(agendaRequest.getFecha());
        agendaActualizada.setHoraInicio(agendaRequest.getHoraInicio());
        agendaActualizada.setHoraFin(agendaRequest.getHoraFin());
        agendaActualizada.setEstado(agendaRequest.getEstado());
        agendaActualizada.setObservacion(agendaRequest.getObservacion());

        validarReglasDeNegocio(agendaRequest);

        log.info("Agenda médica con id {} actualizada correctamente", idAgenda);
        return agendaMapper.toResponse(agendaRepository.save(agendaActualizada));
    }

    private void validarReglasDeNegocio(AgendaRequest agendaRequest) {
        if (agendaRequest.getFecha().getDayOfWeek() == DayOfWeek.SUNDAY) {
            log.error("Intento de crear agenda médica en domingo");
            throw new IllegalArgumentException("No se pueden crear agendas médicas los domingos");
        }

        if (agendaRequest.getHoraInicio().isBefore(LocalTime.of(8, 0))
                || agendaRequest.getHoraFin().isAfter(LocalTime.of(17, 0))) {
            log.error("Horario fuera de atención: {} - {}",
                    agendaRequest.getHoraInicio(), agendaRequest.getHoraFin());
            throw new IllegalArgumentException("La agenda debe estar dentro del horario de atención (08:00 a 17:00)");
        }

        if (!agendaRequest.getHoraFin().isAfter(agendaRequest.getHoraInicio())) {
            log.error("Hora fin menor o igual a hora inicio");
            throw new IllegalArgumentException("La hora de término debe ser posterior a la hora de inicio");
        }

        if (agendaRepository.existsByIdProfesionalAndFechaAndHoraInicio(
                agendaRequest.getIdProfesional(),
                agendaRequest.getFecha(),
                agendaRequest.getHoraInicio())) {
            log.error("Ya existe agenda para profesional {} en fecha {} hora {}",
                    agendaRequest.getIdProfesional(),
                    agendaRequest.getFecha(),
                    agendaRequest.getHoraInicio());
            throw new IllegalArgumentException("Ya existe una agenda para ese profesional en esa fecha y hora");
        }
    }
}