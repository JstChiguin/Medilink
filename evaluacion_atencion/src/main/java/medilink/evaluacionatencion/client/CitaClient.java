package medilink.evaluacionatencion.client;

import medilink.evaluacionatencion.dto.response.CitaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cita-service", path = "/api/v1/citas")
public interface CitaClient {

    @GetMapping("/{idCita}")
    CitaResponse obtenerCitaPorId(@PathVariable Long idCita);
}