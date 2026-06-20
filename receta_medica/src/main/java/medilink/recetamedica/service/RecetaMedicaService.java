package medilink.recetamedica.service;

import lombok.AllArgsConstructor;
import medilink.recetamedica.client.CitaClient;
import medilink.recetamedica.dto.request.RecetaMedicaRequest;
import medilink.recetamedica.dto.response.CitaResponse;
import medilink.recetamedica.dto.response.RecetaMedicaResponse;
import medilink.recetamedica.exception.RecetaMedicaNoEncontrada;
import medilink.recetamedica.mapper.RecetaMedicaMapper;
import medilink.recetamedica.model.entity.RecetaMedica;
import medilink.recetamedica.repository.RecetaMedicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RecetaMedicaService {

    private final RecetaMedicaRepository recetaMedicaRepository;
    private final RecetaMedicaMapper recetaMedicaMapper;
    private final CitaClient citaClient;

    private static final Logger log =
            LoggerFactory.getLogger(RecetaMedicaService.class);

    public List<RecetaMedicaResponse> obtenerTodasLasRecetas() {

        log.info("Obteniendo listado de recetas médicas");

        return recetaMedicaMapper.toResponseList(
                recetaMedicaRepository.findAll());
    }

    public RecetaMedicaResponse obtenerRecetaPorId(Long idReceta) {

        log.info("Obteniendo receta médica con id {}", idReceta);

        return recetaMedicaMapper.toResponse(
                recetaMedicaRepository.findById(idReceta)
                        .orElseThrow(() ->
                                new RecetaMedicaNoEncontrada(idReceta))
        );
    }

    public RecetaMedicaResponse crearReceta(
            RecetaMedicaRequest recetaMedicaRequest) {

        validarCitaExiste(recetaMedicaRequest.getIdCita());
        validarReglasDeNegocio(recetaMedicaRequest);

        log.info("Creando receta médica: {}", recetaMedicaRequest);

        return recetaMedicaMapper.toResponse(
                recetaMedicaRepository.save(
                        recetaMedicaMapper.toEntity(recetaMedicaRequest))
        );
    }

    public RecetaMedicaResponse actualizarReceta(
            Long idReceta,
            RecetaMedicaRequest recetaMedicaRequest) {

        validarCitaExiste(recetaMedicaRequest.getIdCita());
        validarReglasDeNegocio(recetaMedicaRequest);

        RecetaMedica receta = recetaMedicaRepository
                .findById(idReceta)
                .orElseThrow(() ->
                        new RecetaMedicaNoEncontrada(idReceta));

        receta.setIdPaciente(recetaMedicaRequest.getIdPaciente());
        receta.setIdProfesional(recetaMedicaRequest.getIdProfesional());
        receta.setIdCita(recetaMedicaRequest.getIdCita());
        receta.setFechaEmision(recetaMedicaRequest.getFechaEmision());
        receta.setMedicamento(recetaMedicaRequest.getMedicamento());
        receta.setDosis(recetaMedicaRequest.getDosis());
        receta.setFrecuencia(recetaMedicaRequest.getFrecuencia());
        receta.setDuracion(recetaMedicaRequest.getDuracion());
        receta.setIndicaciones(recetaMedicaRequest.getIndicaciones());

        log.info("Receta médica con id {} actualizada",
                idReceta);

        return recetaMedicaMapper.toResponse(
                recetaMedicaRepository.save(receta));
    }

    public boolean eliminarReceta(Long idReceta) {

        if (!recetaMedicaRepository.existsById(idReceta)) {

            log.error("No existe receta médica con id {}",
                    idReceta);

            throw new RecetaMedicaNoEncontrada(idReceta);
        }

        recetaMedicaRepository.deleteById(idReceta);

        log.info("Receta médica con id {} eliminada",
                idReceta);

        return true;
    }

    private void validarCitaExiste(Long idCita) {
        CitaResponse cita = citaClient.obtenerCitaPorId(idCita);

        if (cita == null || cita.getIdCita() == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }
    }

    private void validarReglasDeNegocio(
            RecetaMedicaRequest recetaMedicaRequest) {

        if (recetaMedicaRequest.getFechaEmision()
                .isAfter(LocalDate.now())) {

            log.error("La fecha de emisión no puede ser futura");

            throw new IllegalArgumentException(
                    "La fecha de emisión no puede ser futura");
        }
    }
}