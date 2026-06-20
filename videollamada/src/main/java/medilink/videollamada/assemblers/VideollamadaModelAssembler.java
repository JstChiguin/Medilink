package medilink.videollamada.assemblers;

import medilink.videollamada.controller.VideollamadaControllerV2;
import medilink.videollamada.model.entity.Videollamada;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VideollamadaModelAssembler implements RepresentationModelAssembler<Videollamada, EntityModel<Videollamada>> {

    @Override
    public EntityModel<Videollamada> toModel(Videollamada videollamada) {
        return EntityModel.of(
                videollamada,
                linkTo(methodOn(VideollamadaControllerV2.class)
                        .getVideollamadaById(videollamada.getIdVideollamada()))
                        .withSelfRel(),

                linkTo(methodOn(VideollamadaControllerV2.class)
                        .getAllVideollamadas())
                        .withRel("videollamadas")
        );
    }
}