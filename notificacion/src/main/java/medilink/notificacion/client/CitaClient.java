package medilink.notificacion.client;

import medilink.notificacion.dto.response.CitaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cita-service", url = "http://localhost:3006")
public interface CitaClient {

    @GetMapping("/api/v1/citas/{idCita}")
    CitaResponse obtenerCitaPorId(@PathVariable Long idCita);
}