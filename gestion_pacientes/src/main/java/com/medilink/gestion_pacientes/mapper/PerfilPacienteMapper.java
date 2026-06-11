package com.medilink.gestion_pacientes.mapper;

import com.medilink.gestion_pacientes.dto.request.PerfilPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PerfilPacienteResponse;
import com.medilink.gestion_pacientes.model.PerfilPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PacienteMapper.class, ContactoPacienteMapper.class})
public interface PerfilPacienteMapper {

    @Mapping(target = "idPerfil", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "contactoPaciente", ignore = true)
    @Mapping(target = "ultimaActualizacion", ignore = true)
    PerfilPaciente toEntity(PerfilPacienteRequest perfilPacienteRequest);

    @Mapping(target = "paciente", source = "paciente")
    @Mapping(target = "contacto", source = "contactoPaciente")
    PerfilPacienteResponse toResponse(PerfilPaciente perfilPaciente);
}