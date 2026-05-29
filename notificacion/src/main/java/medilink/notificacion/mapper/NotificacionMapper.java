package medilink.notificacion.mapper;

import medilink.notificacion.dto.request.NotificacionRequest;
import medilink.notificacion.dto.response.NotificacionResponse;
import medilink.notificacion.model.entity.Notificacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    Notificacion toEntity(NotificacionRequest request);

    NotificacionResponse toResponse(Notificacion entity);
}