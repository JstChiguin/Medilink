package medilink.agenda.assemblers;

import medilink.agenda.controller.AgendaControllerV2;
import medilink.agenda.model.entity.AgendaMedica;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AgendaModelAssembler implements RepresentationModelAssembler<AgendaMedica, EntityModel<AgendaMedica>> {

    @Override
    public EntityModel<AgendaMedica> toModel(AgendaMedica agenda) {

        return EntityModel.of(
                agenda,
                linkTo(methodOn(AgendaControllerV2.class)
                        .getAgendaById(agenda.getIdAgenda()))
                        .withSelfRel(),

                linkTo(methodOn(AgendaControllerV2.class)
                        .getAllAgendas())
                        .withRel("agendas")
        );
    }
}