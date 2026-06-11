package medilink.recetamedica.mapper;

import medilink.recetamedica.dto.request.RecetaMedicaRequest;
import medilink.recetamedica.dto.response.RecetaMedicaResponse;
import medilink.recetamedica.model.entity.RecetaMedica;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecetaMedicaMapper {

    RecetaMedica toEntity(RecetaMedicaRequest recetaMedicaRequest);

    RecetaMedicaResponse toResponse(RecetaMedica recetaMedica);

    List<RecetaMedicaResponse> toResponseList(List<RecetaMedica> recetaMedicaList);
}