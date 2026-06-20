package medilink.agenda.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import medilink.agenda.assemblers.AgendaModelAssembler;
import medilink.agenda.model.entity.AgendaMedica;
import medilink.agenda.repository.AgendaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/agendas")
@AllArgsConstructor
@Tag(name = "Agenda Médica", description = "Operaciones relacionadas con la agenda médica")
public class AgendaControllerV2 {

    private final AgendaRepository agendaRepository;
    private final AgendaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<AgendaMedica>> getAllAgendas() {
        List<EntityModel<AgendaMedica>> agendas = agendaRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(agendas,
                linkTo(methodOn(AgendaControllerV2.class).getAllAgendas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<AgendaMedica> getAgendaById(@PathVariable Long id) {
        AgendaMedica agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda médica no encontrada"));

        return assembler.toModel(agenda);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<AgendaMedica>> createAgenda(@RequestBody AgendaMedica agenda) {
        AgendaMedica nuevaAgenda = agendaRepository.save(agenda);

        return ResponseEntity
                .created(linkTo(methodOn(AgendaControllerV2.class).getAgendaById(nuevaAgenda.getIdAgenda())).toUri())
                .body(assembler.toModel(nuevaAgenda));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<AgendaMedica>> updateAgenda(
            @PathVariable Long id,
            @RequestBody AgendaMedica agenda
    ) {
        agenda.setIdAgenda(id);
        AgendaMedica agendaActualizada = agendaRepository.save(agenda);

        return ResponseEntity.ok(assembler.toModel(agendaActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAgenda(@PathVariable Long id) {
        agendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}