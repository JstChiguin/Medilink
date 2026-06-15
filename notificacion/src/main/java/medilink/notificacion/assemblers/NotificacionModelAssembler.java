package medilink.notificacion.assemblers;

import medilink.notificacion.controller.NotificacionControllerV2;
import medilink.notificacion.model.entity.Notificacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    public EntityModel<Notificacion> toModel(Notificacion notificacion) {
        return EntityModel.of(
                notificacion,
                linkTo(methodOn(NotificacionControllerV2.class)
                        .getNotificacionById(notificacion.getIdNotificacion()))
                        .withSelfRel(),

                linkTo(methodOn(NotificacionControllerV2.class)
                        .getAllNotificaciones())
                        .withRel("notificaciones")
        );
    }
}