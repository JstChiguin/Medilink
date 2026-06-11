package com.medilink.gestion_pacientes.mapper;

import com.medilink.gestion_pacientes.dto.request.PacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PacienteResponse;
import com.medilink.gestion_pacientes.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(target = "idPaciente", ignore = true)
    @Mapping(source = "idUsuario", target = "idUsuario")
    Paciente toEntity(PacienteRequest pacienteRequest);

    @Mapping(target = "usuario", ignore = true)
    PacienteResponse toResponse(Paciente paciente);

    @Mapping(target = "usuario", ignore = true)
    List<PacienteResponse> toResponseList(List<Paciente> pacienteList);
}