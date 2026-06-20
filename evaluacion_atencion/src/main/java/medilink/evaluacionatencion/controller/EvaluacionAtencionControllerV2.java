package medilink.evaluacionatencion.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import medilink.evaluacionatencion.assemblers.EvaluacionAtencionModelAssembler;
import medilink.evaluacionatencion.model.entity.EvaluacionAtencion;
import medilink.evaluacionatencion.repository.EvaluacionAtencionRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/evaluaciones")
@AllArgsConstructor
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con las evaluaciones de atención")
public class EvaluacionAtencionControllerV2 {

    private final EvaluacionAtencionRepository evaluacionRepository;
    private final EvaluacionAtencionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<EvaluacionAtencion>> getAllEvaluaciones() {

        List<EntityModel<EvaluacionAtencion>> evaluaciones =
                evaluacionRepository.findAll()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(
                evaluaciones,
                linkTo(methodOn(EvaluacionAtencionControllerV2.class)
                        .getAllEvaluaciones())
                        .withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<EvaluacionAtencion> getEvaluacionById(
            @PathVariable Long id) {

        EvaluacionAtencion evaluacion =
                evaluacionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Evaluación no encontrada"));

        return assembler.toModel(evaluacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EvaluacionAtencion>> createEvaluacion(
            @RequestBody EvaluacionAtencion evaluacion) {

        EvaluacionAtencion nuevaEvaluacion =
                evaluacionRepository.save(evaluacion);

        return ResponseEntity
                .created(
                        linkTo(
                                methodOn(EvaluacionAtencionControllerV2.class)
                                        .getEvaluacionById(
                                                nuevaEvaluacion.getIdEvaluacion()
                                        )
                        ).toUri()
                )
                .body(assembler.toModel(nuevaEvaluacion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EvaluacionAtencion>> updateEvaluacion(
            @PathVariable Long id,
            @RequestBody EvaluacionAtencion evaluacion) {

        evaluacion.setIdEvaluacion(id);

        EvaluacionAtencion evaluacionActualizada =
                evaluacionRepository.save(evaluacion);

        return ResponseEntity.ok(
                assembler.toModel(evaluacionActualizada)
        );
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEvaluacion(
            @PathVariable Long id) {

        evaluacionRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}