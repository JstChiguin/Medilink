package medilink.pago.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import medilink.pago.dto.request.PagoRequest;
import medilink.pago.dto.response.PagoResponse;
import medilink.pago.service.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@AllArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoResponse>> findAll() {
        return ResponseEntity.ok(
                pagoService.obtenerTodosLosPagos()
        );
    }

    @GetMapping("/{idPago}")
    public ResponseEntity<PagoResponse> findById(
            @PathVariable Long idPago) {

        return ResponseEntity.ok(
                pagoService.obtenerPagoPorId(idPago)
        );
    }

    @PostMapping
    public ResponseEntity<PagoResponse> create(
            @Valid @RequestBody PagoRequest pagoRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoService.crearPago(pagoRequest));
    }

    @PutMapping("/{idPago}")
    public ResponseEntity<PagoResponse> update(
            @PathVariable Long idPago,
            @Valid @RequestBody PagoRequest pagoRequest) {

        return ResponseEntity.ok(
                pagoService.actualizarPago(idPago, pagoRequest)
        );
    }

    @DeleteMapping("/{idPago}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idPago) {

        pagoService.eliminarPago(idPago);

        return ResponseEntity.noContent().build();
    }
}