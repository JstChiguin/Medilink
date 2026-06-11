package medilink.videollamada.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.videollamada.dto.request.VideollamadaRequest;
import medilink.videollamada.dto.response.VideollamadaResponse;
import medilink.videollamada.service.VideollamadaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videollamadas")
@AllArgsConstructor
public class VideollamadaController {

    private final VideollamadaService videollamadaService;

    @GetMapping
    public ResponseEntity<List<VideollamadaResponse>> findAll() {
        return ResponseEntity.ok(
                videollamadaService.obtenerTodasLasVideollamadas()
        );
    }

    @GetMapping("/{idVideollamada}")
    public ResponseEntity<VideollamadaResponse> findById(
            @PathVariable Long idVideollamada) {

        return ResponseEntity.ok(
                videollamadaService.obtenerVideollamadaPorId(idVideollamada)
        );
    }

    @PostMapping
    public ResponseEntity<VideollamadaResponse> create(
            @Valid @RequestBody VideollamadaRequest videollamadaRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(videollamadaService.crearVideollamada(videollamadaRequest));
    }

    @PutMapping("/{idVideollamada}")
    public ResponseEntity<VideollamadaResponse> update(
            @PathVariable Long idVideollamada,
            @Valid @RequestBody VideollamadaRequest videollamadaRequest) {

        return ResponseEntity.ok(
                videollamadaService.actualizarVideollamada(
                        idVideollamada,
                        videollamadaRequest
                )
        );
    }

    @DeleteMapping("/{idVideollamada}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idVideollamada) {

        videollamadaService.eliminarVideollamada(idVideollamada);

        return ResponseEntity.noContent().build();
    }
}