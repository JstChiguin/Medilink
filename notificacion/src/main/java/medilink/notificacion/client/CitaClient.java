package medilink.notificacion.client;

import medilink.notificacion.dto.response.CitaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "citas", path =  "/api/v1/citas")
public interface CitaClient {

    @GetMapping("/{idCita}")
    CitaResponse obtenerCitaPorId(@PathVariable Long idCita);
}