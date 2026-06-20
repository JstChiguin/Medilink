package medilink.cita.assemblers;

import medilink.cita.controller.CitaControllerV2;
import medilink.cita.model.entity.Cita;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CitaModelAssembler implements RepresentationModelAssembler<Cita, EntityModel<Cita>> {

    @Override
    public EntityModel<Cita> toModel (Cita cita){
        return EntityModel.of(cita,
                linkTo(methodOn(CitaControllerV2.class).getCitaById(cita.getIdCita())).withSelfRel(),
                linkTo(methodOn(CitaControllerV2.class).getAllCitas()).withRel("citas"));
    }
}
