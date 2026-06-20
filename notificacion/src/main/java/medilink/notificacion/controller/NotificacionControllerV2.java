package medilink.notificacion.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import medilink.notificacion.assemblers.NotificacionModelAssembler;
import medilink.notificacion.model.entity.Notificacion;
import medilink.notificacion.repository.NotificacionRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/notificaciones")
@AllArgsConstructor
@Tag(name = "Notificaciones", description = "Operaciones relacionadas con las notificaciones")
public class NotificacionControllerV2 {

    private final NotificacionRepository notificacionRepository;
    private final NotificacionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Notificacion>> getAllNotificaciones() {
        List<EntityModel<Notificacion>> notificaciones = notificacionRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                notificaciones,
                linkTo(methodOn(NotificacionControllerV2.class).getAllNotificaciones()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Notificacion> getNotificacionById(@PathVariable Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        return assembler.toModel(notificacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> createNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionRepository.save(notificacion);

        return ResponseEntity
                .created(linkTo(methodOn(NotificacionControllerV2.class)
                        .getNotificacionById(nuevaNotificacion.getIdNotificacion()))
                        .toUri())
                .body(assembler.toModel(nuevaNotificacion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> updateNotificacion(
            @PathVariable Long id,
            @RequestBody Notificacion notificacion
    ) {
        notificacion.setIdNotificacion(id);
        Notificacion notificacionActualizada = notificacionRepository.save(notificacion);

        return ResponseEntity.ok(assembler.toModel(notificacionActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteNotificacion(@PathVariable Long id) {
        notificacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}