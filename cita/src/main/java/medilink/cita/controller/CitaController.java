package medilink.cita.controller;

import lombok.AllArgsConstructor;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.model.entity.Cita;
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
    public ResponseEntity<List<Cita>> findAll(){
        return ResponseEntity.ok(citaService.obtenerTodasLasCitas());
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<Cita> findById(@PathVariable Long idCita){
        return ResponseEntity.ok(citaService.obtenerCitaPorId(idCita));
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody CitaRequest citaRequest){
        Cita citaCreada = citaService.crearCita(citaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> deleteById(@PathVariable Long idCita) {
        citaService.eliminarCita(idCita);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<Cita> putById(@PathVariable Long idCita,@RequestBody CitaRequest citaRequest) {
        return ResponseEntity.ok().body(citaService.actualizarCita(idCita, citaRequest));
    }
}
