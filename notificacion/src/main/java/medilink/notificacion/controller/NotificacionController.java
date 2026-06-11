package medilink.notificacion.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.notificacion.dto.request.NotificacionRequest;
import medilink.notificacion.dto.response.NotificacionResponse;
import medilink.notificacion.service.NotificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@AllArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<NotificacionResponse>> findAll() {

        return ResponseEntity.ok(
                notificacionService.obtenerTodasLasNotificaciones()
        );
    }

    @GetMapping("/{idNotificacion}")
    public ResponseEntity<NotificacionResponse> findById(
            @PathVariable Long idNotificacion) {

        return ResponseEntity.ok(
                notificacionService.obtenerNotificacionPorId(idNotificacion)
        );
    }

    @PostMapping
    public ResponseEntity<NotificacionResponse> create(
            @Valid @RequestBody NotificacionRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        notificacionService.crearNotificacion(request)
                );
    }

    @PutMapping("/{idNotificacion}")
    public ResponseEntity<NotificacionResponse> update(
            @PathVariable Long idNotificacion,
            @Valid @RequestBody NotificacionRequest request) {

        return ResponseEntity.ok(
                notificacionService.actualizarNotificacion(
                        idNotificacion,
                        request
                )
        );
    }

    @DeleteMapping("/{idNotificacion}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idNotificacion) {

        notificacionService.eliminarNotificacion(idNotificacion);

        return ResponseEntity.noContent().build();
    }
}