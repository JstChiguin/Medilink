package medilink.videollamada.mapper;

import medilink.videollamada.dto.request.VideollamadaRequest;
import medilink.videollamada.dto.response.VideollamadaResponse;
import medilink.videollamada.model.entity.Videollamada;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideollamadaMapper {

    Videollamada toEntity(VideollamadaRequest videollamadaRequest);

    VideollamadaResponse toResponse(Videollamada videollamada);

    List<VideollamadaResponse> toResponseList(List<Videollamada> videollamadaList);
}