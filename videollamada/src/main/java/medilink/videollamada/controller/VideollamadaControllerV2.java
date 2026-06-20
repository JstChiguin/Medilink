package medilink.videollamada.controller;

import lombok.AllArgsConstructor;
import medilink.videollamada.assemblers.VideollamadaModelAssembler;
import medilink.videollamada.model.entity.Videollamada;
import medilink.videollamada.repository.VideollamadaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/videollamadas")
@AllArgsConstructor
public class VideollamadaControllerV2 {

    private final VideollamadaRepository videollamadaRepository;
    private final VideollamadaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Videollamada>> getAllVideollamadas() {

        var videollamadas = videollamadaRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                videollamadas,
                linkTo(methodOn(VideollamadaControllerV2.class)
                        .getAllVideollamadas())
                        .withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Videollamada> getVideollamadaById(
            @PathVariable Long id) {

        Videollamada videollamada = videollamadaRepository
                .findById(id)
                .orElseThrow();

        return assembler.toModel(videollamada);
    }
}