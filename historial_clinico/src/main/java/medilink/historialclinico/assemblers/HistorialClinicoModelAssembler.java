package medilink.historialclinico.assemblers;

import medilink.historialclinico.controller.HistorialClinicoControllerV2;
import medilink.historialclinico.model.entity.HistorialClinico;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class HistorialClinicoModelAssembler
        implements RepresentationModelAssembler<
        HistorialClinico,
        EntityModel<HistorialClinico>> {

    @Override
    public EntityModel<HistorialClinico> toModel(
            HistorialClinico historial) {

        return EntityModel.of(
                historial,

                linkTo(
                        methodOn(HistorialClinicoControllerV2.class)
                                .getHistorialById(
                                        historial.getIdHistorial()
                                )
                ).withSelfRel(),

                linkTo(
                        methodOn(HistorialClinicoControllerV2.class)
                                .getAllHistoriales()
                ).withRel("historiales")
        );
    }
}