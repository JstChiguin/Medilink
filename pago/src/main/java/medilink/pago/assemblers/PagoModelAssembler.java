package medilink.pago.assemblers;

import medilink.pago.controller.PagoControllerV2;
import medilink.pago.model.entity.Pago;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {

    @Override
    public EntityModel<Pago> toModel(Pago pago) {
        return EntityModel.of(
                pago,
                linkTo(methodOn(PagoControllerV2.class)
                        .getPagoById(pago.getIdPago()))
                        .withSelfRel(),

                linkTo(methodOn(PagoControllerV2.class)
                        .getAllPagos())
                        .withRel("pagos")
        );
    }
}