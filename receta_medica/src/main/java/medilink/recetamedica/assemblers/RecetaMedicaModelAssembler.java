package medilink.recetamedica.assemblers;

import medilink.recetamedica.controller.RecetaMedicaControllerV2;
import medilink.recetamedica.model.entity.RecetaMedica;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RecetaMedicaModelAssembler
        implements RepresentationModelAssembler<RecetaMedica,
        EntityModel<RecetaMedica>> {

    @Override
    public EntityModel<RecetaMedica> toModel(
            RecetaMedica recetaMedica) {

        return EntityModel.of(
                recetaMedica,

                linkTo(
                        methodOn(RecetaMedicaControllerV2.class)
                                .getRecetaById(
                                        recetaMedica.getIdReceta()
                                )
                ).withSelfRel(),

                linkTo(
                        methodOn(RecetaMedicaControllerV2.class)
                                .getAllRecetas()
                ).withRel("recetas")
        );
    }
}