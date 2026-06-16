package medilink.recetamedica.controller;

import lombok.AllArgsConstructor;
import medilink.recetamedica.assemblers.RecetaMedicaModelAssembler;
import medilink.recetamedica.model.entity.RecetaMedica;
import medilink.recetamedica.repository.RecetaMedicaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/recetas")
@AllArgsConstructor
public class RecetaMedicaControllerV2 {

    private final RecetaMedicaRepository recetaMedicaRepository;
    private final RecetaMedicaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<RecetaMedica>> getAllRecetas() {

        var recetas = recetaMedicaRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                recetas,
                linkTo(methodOn(RecetaMedicaControllerV2.class)
                        .getAllRecetas()).withSelfRel()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<RecetaMedica> getRecetaById(
            @PathVariable Long id) {

        RecetaMedica receta = recetaMedicaRepository
                .findById(id)
                .orElseThrow();

        return assembler.toModel(receta);
    }
}