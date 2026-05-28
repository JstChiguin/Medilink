package com.medilink.gestion_pacientes.mapper;


import com.medilink.gestion_pacientes.dto.request.PerfilPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PerfilPacienteResponse;
import com.medilink.gestion_pacientes.model.PerfilPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerfilPacienteMapper {

    @Mapping(target = "idPerfil", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "contactoPaciente", ignore = true)
    PerfilPaciente toEntity(PerfilPacienteRequest perfilPacienteRequest);

    @Mapping(target = "idPaciente", source = "paciente.idPaciente")
    @Mapping(target = "idContacto", source = "contactoPaciente.idContacto")
    PerfilPacienteResponse toResponse(PerfilPaciente perfilPaciente);
}
