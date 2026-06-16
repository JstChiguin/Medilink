package medilink.cita.client;

import medilink.cita.dto.response.AgendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "agenda-service", url = "http://localhost:3004")
public interface AgendaClient {

    @GetMapping("/api/v1/agendas/{idAgenda}")
    AgendaResponse obtenerAgendaPorId(@PathVariable Long idAgenda);
}