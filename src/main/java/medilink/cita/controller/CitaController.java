package medilink.cita.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@AllArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponse>> findAll(){
        return ResponseEntity.ok(citaService.obtenerTodasLasCitas());
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaResponse> findById(@PathVariable Long idCita){
        return ResponseEntity.ok(citaService.obtenerCitaPorId(idCita));
    }

    @PostMapping
    public ResponseEntity<CitaResponse> create(@Valid @RequestBody CitaRequest citaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crearCita(citaRequest));
    }

    @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idCita) {
        citaService.eliminarCita(idCita);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<CitaResponse> putById(@PathVariable Long idCita, @Valid @RequestBody CitaRequest citaRequest) {
        return ResponseEntity.ok(citaService.actualizarCita(idCita, citaRequest));
    }
}
