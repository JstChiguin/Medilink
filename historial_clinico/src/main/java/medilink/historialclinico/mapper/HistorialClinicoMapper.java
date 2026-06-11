package medilink.historialclinico.mapper;

import medilink.historialclinico.dto.request.HistorialClinicoRequest;
import medilink.historialclinico.dto.response.HistorialClinicoResponse;
import medilink.historialclinico.model.entity.HistorialClinico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialClinicoMapper {

    HistorialClinico toEntity(HistorialClinicoRequest historialClinicoRequest);

    HistorialClinicoResponse toResponse(HistorialClinico historialClinico);

    List<HistorialClinicoResponse> toResponseList(
            List<HistorialClinico> historialClinicoList);
}