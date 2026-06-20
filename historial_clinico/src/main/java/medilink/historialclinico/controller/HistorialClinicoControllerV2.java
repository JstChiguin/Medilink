package medilink.historialclinico.controller;

import lombok.AllArgsConstructor;
import medilink.historialclinico.assemblers.HistorialClinicoModelAssembler;
import medilink.historialclinico.model.entity.HistorialClinico;
import medilink.historialclinico.repository.HistorialClinicoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/historiales")
@AllArgsConstructor
public class HistorialClinicoControllerV2 {

    private final HistorialClinicoRepository historialClinicoRepository;
    private final HistorialClinicoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<HistorialClinico>>
    getAllHistoriales() {

        var historiales = historialClinicoRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                historiales,
                linkTo(
                        methodOn(HistorialClinicoControllerV2.class)
                                .getAllHistoriales()
                ).withSelfRel()
        );
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaTypes.HAL_JSON_VALUE
    )
    public EntityModel<HistorialClinico> getHistorialById(
            @PathVariable Long id) {

        HistorialClinico historial =
                historialClinicoRepository.findById(id)
                        .orElseThrow();

        return assembler.toModel(historial);
    }
}