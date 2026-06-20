package medilink.agenda.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.agenda.dto.request.AgendaRequest;
import medilink.agenda.dto.response.AgendaResponse;
import medilink.agenda.service.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agendas")
@AllArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> findAll() {
        return ResponseEntity.ok(agendaService.obtenerTodasLasAgendas());
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity<AgendaResponse> findById(@PathVariable Long idAgenda) {
        return ResponseEntity.ok(agendaService.obtenerAgendaPorId(idAgenda));
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> create(@Valid @RequestBody AgendaRequest agendaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.crearAgenda(agendaRequest));
    }

    @DeleteMapping("/{idAgenda}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idAgenda) {
        agendaService.eliminarAgenda(idAgenda);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<AgendaResponse> putById(
            @PathVariable Long idAgenda,
            @Valid @RequestBody AgendaRequest agendaRequest
    ) {
        return ResponseEntity.ok(agendaService.actualizarAgenda(idAgenda, agendaRequest));
    }
}