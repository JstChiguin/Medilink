package medilink.cita.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import medilink.cita.assemblers.CitaModelAssembler;
import medilink.cita.model.entity.Cita;
import medilink.cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/citas")
@AllArgsConstructor
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas")

public class CitaControllerV2 {

    @Autowired
    private final CitaRepository citaRepository;

    @Autowired
    private CitaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cita>> getAllCitas() {
        List<EntityModel<Cita>> citas = citaRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(citas,
                linkTo(methodOn(CitaControllerV2.class).getAllCitas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Cita> getCitaById(@PathVariable Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return assembler.toModel(cita);
    }
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cita>> createCita(@RequestBody Cita cita) {
        Cita newCita = citaRepository.save(cita);
        return ResponseEntity
                .created(linkTo(methodOn(CitaControllerV2.class).getCitaById(newCita.getIdCita())).toUri())
                .body(assembler.toModel(newCita));
    }
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cita>> updateCita(@PathVariable Long id, @RequestBody Cita cita) {
        cita.setIdCita(id);
        Cita updatedCita = citaRepository.save(cita);
        return ResponseEntity
                .ok(assembler.toModel(updatedCita));
    }
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        citaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



