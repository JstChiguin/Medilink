package medilink.videollamada.service;

import lombok.AllArgsConstructor;
import medilink.videollamada.client.CitaClient;
import medilink.videollamada.dto.request.VideollamadaRequest;
import medilink.videollamada.dto.response.CitaResponse;
import medilink.videollamada.dto.response.VideollamadaResponse;
import medilink.videollamada.exception.VideollamadaNoEncontrada;
import medilink.videollamada.mapper.VideollamadaMapper;
import medilink.videollamada.model.entity.Videollamada;
import medilink.videollamada.repository.VideollamadaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class VideollamadaService {

    private final VideollamadaRepository videollamadaRepository;
    private final VideollamadaMapper videollamadaMapper;
    private final CitaClient citaClient;

    private static final Logger log =
            LoggerFactory.getLogger(VideollamadaService.class);

    public List<VideollamadaResponse> obtenerTodasLasVideollamadas() {
        log.info("Obteniendo listado de videollamadas");

        return videollamadaMapper.toResponseList(
                videollamadaRepository.findAll());
    }

    public VideollamadaResponse obtenerVideollamadaPorId(Long idVideollamada) {
        log.info("Obteniendo videollamada con id {}", idVideollamada);

        return videollamadaMapper.toResponse(
                videollamadaRepository.findById(idVideollamada)
                        .orElseThrow(() ->
                                new VideollamadaNoEncontrada(idVideollamada))
        );
    }

    public VideollamadaResponse crearVideollamada(
            VideollamadaRequest videollamadaRequest) {

        validarCitaExiste(videollamadaRequest.getIdCita());
        validarReglasDeNegocio(videollamadaRequest);

        log.info("Creando videollamada: {}", videollamadaRequest);

        return videollamadaMapper.toResponse(
                videollamadaRepository.save(
                        videollamadaMapper.toEntity(videollamadaRequest))
        );
    }

    public VideollamadaResponse actualizarVideollamada(
            Long idVideollamada,
            VideollamadaRequest videollamadaRequest) {

        validarCitaExiste(videollamadaRequest.getIdCita());
        validarReglasDeNegocio(videollamadaRequest);

        Videollamada videollamada = videollamadaRepository
                .findById(idVideollamada)
                .orElseThrow(() ->
                        new VideollamadaNoEncontrada(idVideollamada));

        videollamada.setIdCita(videollamadaRequest.getIdCita());
        videollamada.setFechaProgramada(videollamadaRequest.getFechaProgramada());
        videollamada.setHoraInicio(videollamadaRequest.getHoraInicio());
        videollamada.setHoraTermino(videollamadaRequest.getHoraTermino());
        videollamada.setEnlaceSesion(videollamadaRequest.getEnlaceSesion());
        videollamada.setEstadoVideollamada(videollamadaRequest.getEstadoVideollamada());

        log.info("Videollamada con id {} actualizada", idVideollamada);

        return videollamadaMapper.toResponse(
                videollamadaRepository.save(videollamada)
        );
    }

    public boolean eliminarVideollamada(Long idVideollamada) {
        if (!videollamadaRepository.existsById(idVideollamada)) {
            log.error("No existe videollamada con id {}", idVideollamada);
            throw new VideollamadaNoEncontrada(idVideollamada);
        }

        videollamadaRepository.deleteById(idVideollamada);

        log.info("Videollamada con id {} eliminada", idVideollamada);

        return true;
    }

    private void validarCitaExiste(Long idCita) {
        CitaResponse cita = citaClient.obtenerCitaPorId(idCita);

        if (cita == null || cita.getIdCita() == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }
    }

    private void validarReglasDeNegocio(
            VideollamadaRequest videollamadaRequest) {

        if (videollamadaRequest.getFechaProgramada()
                .isBefore(LocalDate.now())) {

            throw new IllegalArgumentException(
                    "La fecha programada no puede ser anterior a hoy");
        }

        if (videollamadaRequest.getHoraTermino() != null &&
                videollamadaRequest.getHoraTermino()
                        .isBefore(videollamadaRequest.getHoraInicio())) {

            throw new IllegalArgumentException(
                    "La hora de término no puede ser anterior a la hora de inicio");
        }
    }
}