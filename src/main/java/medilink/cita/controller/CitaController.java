package medilink.cita.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.model.entity.Cita;
import medilink.cita.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@AllArgsConstructor
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas")
public class CitaController {

    //@Autowired
    private final CitaService citaService;

    @GetMapping
    @Operation(summary = "Obtener todas las citas", description = "Obtener una lista de todas las citas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de citaciónes obtenidas exitosamente",
                    content = @Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    public ResponseEntity<List<CitaResponse>> findAll(){
        return ResponseEntity.ok(citaService.obtenerTodasLasCitas());
    }

    @GetMapping("/{idCita}")
    @Operation(summary = "Obtener una cita", description = "Obtener una cita por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citación por id obtenida exitosamente",
                    content = @Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    public ResponseEntity<CitaResponse> findById(@PathVariable Long idCita){
        return ResponseEntity.ok(citaService.obtenerCitaPorId(idCita));
    }

    @PostMapping
    @Operation(summary = "Crear una cita", description = "Crear una cita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citación creada exitosamente",
                    content = @Content(mediaType = "aplication/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    public ResponseEntity<CitaResponse> create(@Valid @RequestBody CitaRequest citaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crearCita(citaRequest));
    }

    @DeleteMapping("/{idCita}")
    @Operation(summary = "Eliminar una cita", description = "Elimina una cita por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Citación no encontrada")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long idCita) {
        citaService.eliminarCita(idCita);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCita}")
    @Operation(summary = "Actualizar una cita", description = "Actualizar una cita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citación actualizada exitosamente",
                content = @Content(mediaType = "aplication/json",
                                    schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Citación no encontrada")
    })
    public ResponseEntity<CitaResponse> putById(@PathVariable Long idCita, @Valid @RequestBody CitaRequest citaRequest) {
        return ResponseEntity.ok(citaService.actualizarCita(idCita, citaRequest));
    }
}
