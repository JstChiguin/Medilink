package medilink.historialclinico.service;

import lombok.AllArgsConstructor;
import medilink.historialclinico.dto.request.HistorialClinicoRequest;
import medilink.historialclinico.dto.response.HistorialClinicoResponse;
import medilink.historialclinico.exception.HistorialClinicoNoEncontrado;
import medilink.historialclinico.mapper.HistorialClinicoMapper;
import medilink.historialclinico.model.entity.HistorialClinico;
import medilink.historialclinico.repository.HistorialClinicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HistorialClinicoService {

    private final HistorialClinicoRepository historialClinicoRepository;
    private final HistorialClinicoMapper historialClinicoMapper;

    private static final Logger log =
            LoggerFactory.getLogger(HistorialClinicoService.class);

    public List<HistorialClinicoResponse> obtenerTodosLosHistoriales() {
        log.info("Obteniendo listado de historiales clínicos");
        return historialClinicoMapper.toResponseList(
                historialClinicoRepository.findAll());
    }

    public HistorialClinicoResponse obtenerHistorialPorId(Long idHistorial) {

        log.info("Obteniendo historial clínico con id {}", idHistorial);

        return historialClinicoMapper.toResponse(
                historialClinicoRepository
                        .findById(idHistorial)
                        .orElseThrow(() ->
                                new HistorialClinicoNoEncontrado(idHistorial))
        );
    }

    public HistorialClinicoResponse crearHistorial(
            HistorialClinicoRequest historialClinicoRequest) {

        validarReglasDeNegocio(historialClinicoRequest);

        log.info("Creando historial clínico: {}",
                historialClinicoRequest);

        return historialClinicoMapper.toResponse(
                historialClinicoRepository.save(
                        historialClinicoMapper.toEntity(
                                historialClinicoRequest))
        );
    }

    public boolean eliminarHistorial(Long idHistorial) {

        if (!historialClinicoRepository.existsById(idHistorial)) {

            log.error("No existe historial clínico con id {}",
                    idHistorial);

            throw new HistorialClinicoNoEncontrado(idHistorial);
        }

        historialClinicoRepository.deleteById(idHistorial);

        log.info("Historial clínico con id {} eliminado",
                idHistorial);

        return true;
    }

    public HistorialClinicoResponse actualizarHistorial(
            Long idHistorial,
            HistorialClinicoRequest historialClinicoRequest) {

        HistorialClinico historial = historialClinicoRepository
                .findById(idHistorial)
                .orElseThrow(() ->
                        new HistorialClinicoNoEncontrado(idHistorial));

        historial.setDiagnostico(
                historialClinicoRequest.getDiagnostico());

        historial.setObservaciones(
                historialClinicoRequest.getObservaciones());

        historial.setRecomendaciones(
                historialClinicoRequest.getRecomendaciones());

        log.info("Historial clínico con id {} actualizado",
                idHistorial);

        return historialClinicoMapper.toResponse(
                historialClinicoRepository.save(historial));
    }

    private void validarReglasDeNegocio(
            HistorialClinicoRequest historialClinicoRequest) {

        if (historialClinicoRequest.getFechaAtencion()
                .isAfter(LocalDate.now())) {

            log.error("La fecha de atención no puede ser futura");

            throw new IllegalArgumentException(
                    "La fecha de atención no puede ser futura");
        }
    }
}