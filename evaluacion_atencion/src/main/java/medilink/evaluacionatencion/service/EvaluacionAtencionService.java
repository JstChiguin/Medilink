package medilink.evaluacionatencion.service;

import lombok.AllArgsConstructor;
import medilink.evaluacionatencion.client.CitaClient;
import medilink.evaluacionatencion.dto.request.EvaluacionAtencionRequest;
import medilink.evaluacionatencion.dto.response.CitaResponse;
import medilink.evaluacionatencion.dto.response.EvaluacionAtencionResponse;
import medilink.evaluacionatencion.exception.EvaluacionAtencionNoEncontrada;
import medilink.evaluacionatencion.mapper.EvaluacionAtencionMapper;
import medilink.evaluacionatencion.model.entity.EvaluacionAtencion;
import medilink.evaluacionatencion.repository.EvaluacionAtencionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluacionAtencionService {

    private final EvaluacionAtencionRepository evaluacionAtencionRepository;
    private final EvaluacionAtencionMapper evaluacionAtencionMapper;
    private final CitaClient citaClient;

    private static final Logger log =
            LoggerFactory.getLogger(EvaluacionAtencionService.class);

    public List<EvaluacionAtencionResponse> obtenerTodasLasEvaluaciones() {
        log.info("Obteniendo listado de evaluaciones de atención");

        return evaluacionAtencionMapper.toResponseList(
                evaluacionAtencionRepository.findAll());
    }

    public EvaluacionAtencionResponse obtenerEvaluacionPorId(Long idEvaluacion) {
        log.info("Obteniendo evaluación de atención con id {}", idEvaluacion);

        return evaluacionAtencionMapper.toResponse(
                evaluacionAtencionRepository.findById(idEvaluacion)
                        .orElseThrow(() ->
                                new EvaluacionAtencionNoEncontrada(idEvaluacion))
        );
    }

    public EvaluacionAtencionResponse crearEvaluacion(
            EvaluacionAtencionRequest evaluacionAtencionRequest) {

        validarCitaExiste(evaluacionAtencionRequest.getIdCita());
        validarReglasDeNegocio(evaluacionAtencionRequest);

        log.info("Creando evaluación de atención: {}", evaluacionAtencionRequest);

        return evaluacionAtencionMapper.toResponse(
                evaluacionAtencionRepository.save(
                        evaluacionAtencionMapper.toEntity(evaluacionAtencionRequest))
        );
    }

    public EvaluacionAtencionResponse actualizarEvaluacion(
            Long idEvaluacion,
            EvaluacionAtencionRequest evaluacionAtencionRequest) {

        validarCitaExiste(evaluacionAtencionRequest.getIdCita());
        validarReglasDeNegocio(evaluacionAtencionRequest);

        EvaluacionAtencion evaluacion = evaluacionAtencionRepository
                .findById(idEvaluacion)
                .orElseThrow(() ->
                        new EvaluacionAtencionNoEncontrada(idEvaluacion));

        evaluacion.setIdCita(evaluacionAtencionRequest.getIdCita());
        evaluacion.setIdPaciente(evaluacionAtencionRequest.getIdPaciente());
        evaluacion.setCalificacion(evaluacionAtencionRequest.getCalificacion());
        evaluacion.setComentario(evaluacionAtencionRequest.getComentario());
        evaluacion.setFechaEvaluacion(evaluacionAtencionRequest.getFechaEvaluacion());

        log.info("Evaluación de atención con id {} actualizada", idEvaluacion);

        return evaluacionAtencionMapper.toResponse(
                evaluacionAtencionRepository.save(evaluacion));
    }

    public boolean eliminarEvaluacion(Long idEvaluacion) {

        if (!evaluacionAtencionRepository.existsById(idEvaluacion)) {
            log.error("No existe evaluación de atención con id {}", idEvaluacion);
            throw new EvaluacionAtencionNoEncontrada(idEvaluacion);
        }

        evaluacionAtencionRepository.deleteById(idEvaluacion);

        log.info("Evaluación de atención con id {} eliminada", idEvaluacion);

        return true;
    }

    private void validarCitaExiste(Long idCita) {
        CitaResponse cita = citaClient.obtenerCitaPorId(idCita);

        if (cita == null || cita.getIdCita() == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }
    }

    private void validarReglasDeNegocio(
            EvaluacionAtencionRequest evaluacionAtencionRequest) {

        if (evaluacionAtencionRequest.getFechaEvaluacion()
                .isAfter(LocalDate.now())) {

            log.error("La fecha de evaluación no puede ser futura");

            throw new IllegalArgumentException(
                    "La fecha de evaluación no puede ser futura");
        }

        if (evaluacionAtencionRequest.getCalificacion() < 1 ||
                evaluacionAtencionRequest.getCalificacion() > 5) {

            log.error("Calificación fuera de rango: {}",
                    evaluacionAtencionRequest.getCalificacion());

            throw new IllegalArgumentException(
                    "La calificación debe estar entre 1 y 5");
        }
    }
}