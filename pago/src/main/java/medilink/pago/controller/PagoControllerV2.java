package medilink.pago.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import medilink.pago.assemblers.PagoModelAssembler;
import medilink.pago.model.entity.Pago;
import medilink.pago.repository.PagoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pagos")
@AllArgsConstructor
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos")
public class PagoControllerV2 {

    private final PagoRepository pagoRepository;
    private final PagoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pago>> getAllPagos() {
        List<EntityModel<Pago>> pagos = pagoRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                pagos,
                linkTo(methodOn(PagoControllerV2.class).getAllPagos()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pago> getPagoById(@PathVariable Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        return assembler.toModel(pago);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pago>> createPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoRepository.save(pago);

        return ResponseEntity
                .created(linkTo(methodOn(PagoControllerV2.class)
                        .getPagoById(nuevoPago.getIdPago()))
                        .toUri())
                .body(assembler.toModel(nuevoPago));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pago>> updatePago(
            @PathVariable Long id,
            @RequestBody Pago pago
    ) {
        pago.setIdPago(id);
        Pago pagoActualizado = pagoRepository.save(pago);

        return ResponseEntity.ok(assembler.toModel(pagoActualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}