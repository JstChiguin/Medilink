package medilink.pago.service;

import feign.FeignException;
import lombok.AllArgsConstructor;
import medilink.pago.client.CitaClient;
import medilink.pago.dto.request.PagoRequest;
import medilink.pago.dto.response.CitaResponse;
import medilink.pago.dto.response.PagoResponse;
import medilink.pago.exception.CitaNoEncontrada;
import medilink.pago.exception.PagoNoEncontrado;
import medilink.pago.mapper.PagoMapper;
import medilink.pago.model.entity.Pago;
import medilink.pago.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final CitaClient citaClient;

    private static final Logger log =
            LoggerFactory.getLogger(PagoService.class);

    public List<PagoResponse> obtenerTodosLosPagos() {

        log.info("Obteniendo listado de pagos");

        return pagoMapper.toResponseList(
                pagoRepository.findAll());
    }

    public PagoResponse obtenerPagoPorId(Long idPago) {

        log.info("Obteniendo pago con id {}", idPago);

        return pagoMapper.toResponse(
                pagoRepository.findById(idPago)
                        .orElseThrow(() -> {
                            log.error("No existe pago con id {}", idPago);
                            return new PagoNoEncontrado(idPago);
                        })
        );
    }

    public PagoResponse crearPago(PagoRequest pagoRequest) {

        log.info("Iniciando creación de pago para cita {}", pagoRequest.getIdCita());

        validarCitaExistente(pagoRequest.getIdCita());
        validarReglasDeNegocio(pagoRequest);

        log.info("Pago validado correctamente. Guardando pago para cita {}", pagoRequest.getIdCita());

        return pagoMapper.toResponse(
                pagoRepository.save(
                        pagoMapper.toEntity(pagoRequest))
        );
    }

    public PagoResponse actualizarPago(
            Long idPago,
            PagoRequest pagoRequest) {

        log.info("Iniciando actualización de pago con id {}", idPago);

        validarCitaExistente(pagoRequest.getIdCita());
        validarReglasDeNegocio(pagoRequest);

        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> {
                    log.error("No existe pago con id {}", idPago);
                    return new PagoNoEncontrado(idPago);
                });

        pago.setIdPaciente(pagoRequest.getIdPaciente());
        pago.setIdProfesional(pagoRequest.getIdProfesional());
        pago.setIdCita(pagoRequest.getIdCita());
        pago.setFechaPago(pagoRequest.getFechaPago());
        pago.setMonto(pagoRequest.getMonto());
        pago.setMetodoPago(pagoRequest.getMetodoPago());
        pago.setEstadoPago(pagoRequest.getEstadoPago());

        log.info("Pago con id {} actualizado correctamente", idPago);

        return pagoMapper.toResponse(
                pagoRepository.save(pago));
    }

    public boolean eliminarPago(Long idPago) {

        log.warn("Solicitando eliminación de pago con id {}", idPago);

        if (!pagoRepository.existsById(idPago)) {

            log.error("No existe pago con id {}", idPago);

            throw new PagoNoEncontrado(idPago);
        }

        pagoRepository.deleteById(idPago);

        log.info("Pago con id {} eliminado", idPago);

        return true;
    }

    private void validarReglasDeNegocio(PagoRequest pagoRequest) {

        if (pagoRequest.getFechaPago()
                .isAfter(LocalDate.now())) {

            log.error("Fecha de pago futura detectada: {}", pagoRequest.getFechaPago());

            throw new IllegalArgumentException(
                    "La fecha de pago no puede ser futura");
        }

        if (pagoRequest.getMonto() <= 0) {

            log.error("Monto inválido detectado: {}", pagoRequest.getMonto());

            throw new IllegalArgumentException(
                    "El monto debe ser mayor a cero");
        }
    }

    private void validarCitaExistente(Long idCita) {

        try {
            log.info("Consultando microservicio Cita para validar id {}", idCita);

            CitaResponse cita = citaClient.obtenerCitaPorId(idCita);

            if (cita == null || cita.getIdCita() == null) {
                log.error("La cita {} no existe o la respuesta fue inválida", idCita);
                throw new CitaNoEncontrada(idCita);
            }

            log.info("Cita {} validada correctamente desde microservicio Cita", idCita);

        } catch (FeignException.NotFound ex) {

            log.error("El microservicio Cita respondió 404 para id {}", idCita);

            throw new CitaNoEncontrada(idCita);
        }
    }
}