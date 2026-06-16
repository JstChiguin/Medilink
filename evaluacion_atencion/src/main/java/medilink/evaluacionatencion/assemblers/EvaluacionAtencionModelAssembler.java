package medilink.evaluacionatencion.assemblers;

import medilink.evaluacionatencion.controller.EvaluacionAtencionControllerV2;
import medilink.evaluacionatencion.model.entity.EvaluacionAtencion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EvaluacionAtencionModelAssembler implements RepresentationModelAssembler<EvaluacionAtencion, EntityModel<EvaluacionAtencion>> {

    @Override
    public EntityModel<EvaluacionAtencion> toModel(EvaluacionAtencion evaluacion) {
        return EntityModel.of(
                evaluacion,
                linkTo(methodOn(EvaluacionAtencionControllerV2.class)
                        .getEvaluacionById(evaluacion.getIdEvaluacion()))
                        .withSelfRel(),

                linkTo(methodOn(EvaluacionAtencionControllerV2.class)
                        .getAllEvaluaciones())
                        .withRel("evaluaciones")
        );
    }
}