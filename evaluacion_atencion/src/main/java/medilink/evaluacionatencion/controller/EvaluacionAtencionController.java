package medilink.evaluacionatencion.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.evaluacionatencion.dto.request.EvaluacionAtencionRequest;
import medilink.evaluacionatencion.dto.response.EvaluacionAtencionResponse;
import medilink.evaluacionatencion.service.EvaluacionAtencionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@AllArgsConstructor
public class EvaluacionAtencionController {

    private final EvaluacionAtencionService evaluacionAtencionService;

    @GetMapping
    public ResponseEntity<List<EvaluacionAtencionResponse>> findAll() {
        return ResponseEntity.ok(
                evaluacionAtencionService.obtenerTodasLasEvaluaciones()
        );
    }

    @GetMapping("/{idEvaluacion}")
    public ResponseEntity<EvaluacionAtencionResponse> findById(
            @PathVariable Long idEvaluacion) {

        return ResponseEntity.ok(
                evaluacionAtencionService.obtenerEvaluacionPorId(idEvaluacion)
        );
    }

    @PostMapping
    public ResponseEntity<EvaluacionAtencionResponse> create(
            @Valid @RequestBody EvaluacionAtencionRequest evaluacionAtencionRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(evaluacionAtencionService.crearEvaluacion(
                        evaluacionAtencionRequest));
    }

    @PutMapping("/{idEvaluacion}")
    public ResponseEntity<EvaluacionAtencionResponse> update(
            @PathVariable Long idEvaluacion,
            @Valid @RequestBody EvaluacionAtencionRequest evaluacionAtencionRequest) {

        return ResponseEntity.ok(
                evaluacionAtencionService.actualizarEvaluacion(
                        idEvaluacion,
                        evaluacionAtencionRequest)
        );
    }

    @DeleteMapping("/{idEvaluacion}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idEvaluacion) {

        evaluacionAtencionService.eliminarEvaluacion(idEvaluacion);

        return ResponseEntity.noContent().build();
    }
}