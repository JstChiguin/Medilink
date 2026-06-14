package medilink.agenda.mapper;

import medilink.agenda.dto.request.AgendaRequest;
import medilink.agenda.dto.response.AgendaResponse;
import medilink.agenda.model.entity.AgendaMedica;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    AgendaMedica toEntity(AgendaRequest agendaRequest);

    AgendaResponse toResponse(AgendaMedica agendaMedica);

    List<AgendaResponse> toResponseList(List<AgendaMedica> agendaMedicaList);
}