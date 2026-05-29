package medilink.historialclinico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.historialclinico.dto.request.HistorialClinicoRequest;
import medilink.historialclinico.dto.response.HistorialClinicoResponse;
import medilink.historialclinico.service.HistorialClinicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historiales")
@AllArgsConstructor
public class HistorialClinicoController {

    private final HistorialClinicoService historialClinicoService;

    @GetMapping
    public ResponseEntity<List<HistorialClinicoResponse>> findAll() {
        return ResponseEntity.ok(
                historialClinicoService.obtenerTodosLosHistoriales()
        );
    }

    @GetMapping("/{idHistorial}")
    public ResponseEntity<HistorialClinicoResponse> findById(
            @PathVariable Long idHistorial) {

        return ResponseEntity.ok(
                historialClinicoService.obtenerHistorialPorId(idHistorial)
        );
    }

    @PostMapping
    public ResponseEntity<HistorialClinicoResponse> create(
            @Valid @RequestBody HistorialClinicoRequest historialClinicoRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(historialClinicoService.crearHistorial(
                        historialClinicoRequest));
    }

    @PutMapping("/{idHistorial}")
    public ResponseEntity<HistorialClinicoResponse> update(
            @PathVariable Long idHistorial,
            @Valid @RequestBody HistorialClinicoRequest historialClinicoRequest) {

        return ResponseEntity.ok(
                historialClinicoService.actualizarHistorial(
                        idHistorial,
                        historialClinicoRequest)
        );
    }

    @DeleteMapping("/{idHistorial}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idHistorial) {

        historialClinicoService.eliminarHistorial(idHistorial);

        return ResponseEntity.noContent().build();
    }
}