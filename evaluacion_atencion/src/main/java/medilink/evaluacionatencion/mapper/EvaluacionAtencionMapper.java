package medilink.evaluacionatencion.mapper;

import medilink.evaluacionatencion.dto.request.EvaluacionAtencionRequest;
import medilink.evaluacionatencion.dto.response.EvaluacionAtencionResponse;
import medilink.evaluacionatencion.model.entity.EvaluacionAtencion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EvaluacionAtencionMapper {

    EvaluacionAtencion toEntity(
            EvaluacionAtencionRequest evaluacionAtencionRequest);

    EvaluacionAtencionResponse toResponse(
            EvaluacionAtencion evaluacionAtencion);

    List<EvaluacionAtencionResponse> toResponseList(
            List<EvaluacionAtencion> evaluacionAtencionList);
}