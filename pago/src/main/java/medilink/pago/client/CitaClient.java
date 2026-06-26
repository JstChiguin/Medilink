package medilink.pago.client;

import medilink.pago.dto.response.CitaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cita", path = "/api/v1/citas")
public interface CitaClient {

    @GetMapping("/{idCita}")
    CitaResponse obtenerCitaPorId(@PathVariable Long idCita);
}