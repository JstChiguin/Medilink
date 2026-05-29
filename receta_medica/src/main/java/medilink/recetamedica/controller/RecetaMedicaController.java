package medilink.recetamedica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.recetamedica.dto.request.RecetaMedicaRequest;
import medilink.recetamedica.dto.response.RecetaMedicaResponse;
import medilink.recetamedica.service.RecetaMedicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@AllArgsConstructor
public class RecetaMedicaController {

    private final RecetaMedicaService recetaMedicaService;

    @GetMapping
    public ResponseEntity<List<RecetaMedicaResponse>> findAll() {
        return ResponseEntity.ok(recetaMedicaService.obtenerTodasLasRecetas());
    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<RecetaMedicaResponse> findById(@PathVariable Long idReceta) {
        return ResponseEntity.ok(recetaMedicaService.obtenerRecetaPorId(idReceta));
    }

    @PostMapping
    public ResponseEntity<RecetaMedicaResponse> create(
            @Valid @RequestBody RecetaMedicaRequest recetaMedicaRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recetaMedicaService.crearReceta(recetaMedicaRequest));
    }

    @PutMapping("/{idReceta}")
    public ResponseEntity<RecetaMedicaResponse> update(
            @PathVariable Long idReceta,
            @Valid @RequestBody RecetaMedicaRequest recetaMedicaRequest) {

        return ResponseEntity.ok(
                recetaMedicaService.actualizarReceta(idReceta, recetaMedicaRequest)
        );
    }

    @DeleteMapping("/{idReceta}")
    public ResponseEntity<Void> delete(@PathVariable Long idReceta) {
        recetaMedicaService.eliminarReceta(idReceta);
        return ResponseEntity.noContent().build();
    }
}