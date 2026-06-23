package com.medilink.gestion_pacientes.mapper;


import com.medilink.gestion_pacientes.dto.request.ContactoPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.ContactoPacienteResponse;
import com.medilink.gestion_pacientes.model.entity.ContactoPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactoPacienteMapper {

    @Mapping(target = "idContacto", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    ContactoPaciente toEntity(ContactoPacienteRequest contactoPacienteRequest);

    @Mapping(target = "idPaciente", source = "paciente.idPaciente")
    ContactoPacienteResponse toResponse(ContactoPaciente contactoPaciente);
}
