package medilink.cita.mapper;

import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.model.entity.Cita;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    Cita toEntity (CitaRequest citaRequest);
    CitaResponse toResponse(Cita cita);
    List<CitaResponse> toResponseList(List<Cita> citaList);
}
