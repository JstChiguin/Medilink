package medilink.pago.mapper;

import medilink.pago.dto.request.PagoRequest;
import medilink.pago.dto.response.PagoResponse;
import medilink.pago.model.entity.Pago;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    Pago toEntity(PagoRequest pagoRequest);

    PagoResponse toResponse(Pago pago);

    List<PagoResponse> toResponseList(List<Pago> pagoList);
}