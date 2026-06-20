package medilink.cita.client;

import medilink.cita.dto.response.AgendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "agenda-service", path = "/api/v1/agendas")
public interface AgendaClient {

    @GetMapping("/{idAgenda}")
    AgendaResponse obtenerAgendaPorId(@PathVariable Long idAgenda);
}