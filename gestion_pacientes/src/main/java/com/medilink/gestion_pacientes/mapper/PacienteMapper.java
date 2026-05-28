package com.medilink.gestion_pacientes.mapper;

import com.medilink.gestion_pacientes.dto.request.PacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PacienteResponse;
import com.medilink.gestion_pacientes.model.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(PacienteRequest pacienteRequest);

    PacienteResponse toResponse(Paciente paciente);

    List<PacienteResponse> toResponseList(List<Paciente> pacienteList);
}