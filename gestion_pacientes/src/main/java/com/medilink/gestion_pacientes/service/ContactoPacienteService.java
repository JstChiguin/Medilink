package com.medilink.gestion_pacientes.service;


import com.medilink.gestion_pacientes.dto.request.ContactoPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.ContactoPacienteResponse;
import com.medilink.gestion_pacientes.exception.ContactoNoEncontradoException;
import com.medilink.gestion_pacientes.exception.PacienteNoEncontradoException;
import com.medilink.gestion_pacientes.mapper.ContactoPacienteMapper;
import com.medilink.gestion_pacientes.model.entity.ContactoPaciente;
import com.medilink.gestion_pacientes.model.entity.Paciente;
import com.medilink.gestion_pacientes.repository.ContactoPacienteRepository;
import com.medilink.gestion_pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoPacienteService {

    private final ContactoPacienteRepository contactoPacienteRepository;
    private final PacienteRepository pacienteRepository;
    private final ContactoPacienteMapper contactoPacienteMapper;

    public List<ContactoPacienteResponse> listarContactos() {
        return contactoPacienteRepository.findAll()
                .stream()
                .map(contactoPacienteMapper::toResponse)
                .toList();
    }

    public ContactoPacienteResponse crearContacto(ContactoPacienteRequest contactoPacienteRequest) {
        Paciente paciente = pacienteRepository
                .findById(contactoPacienteRequest.getIdPaciente())
                .orElseThrow(() -> new PacienteNoEncontradoException(contactoPacienteRequest.getIdPaciente()));

        ContactoPaciente contacto = contactoPacienteMapper.toEntity(contactoPacienteRequest);
        contacto.setPaciente(paciente);

        return contactoPacienteMapper.toResponse(contactoPacienteRepository.save(contacto));
    }

    public ContactoPacienteResponse actualizarContacto(Long idPaciente, ContactoPacienteRequest contactoPacienteRequest) {
        ContactoPaciente contactoExistente = contactoPacienteRepository
                .findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new ContactoNoEncontradoException(idPaciente));

        contactoExistente.setTelefono(contactoPacienteRequest.getTelefono());
        contactoExistente.setDireccionPaciente(contactoPacienteRequest.getDireccionPaciente());
        contactoExistente.setNombreContactoEmergencia(contactoPacienteRequest.getNombreContactoEmergencia());
        contactoExistente.setTelefonoContactoEmergencia(contactoPacienteRequest.getTelefonoContactoEmergencia());

        return contactoPacienteMapper.toResponse(contactoPacienteRepository.save(contactoExistente));
    }

    public ContactoPacienteResponse obtenerContactoPorPaciente(Long idPaciente) {
        return contactoPacienteMapper.toResponse(
                contactoPacienteRepository
                        .findByPaciente_IdPaciente(idPaciente)
                        .orElseThrow(() -> new ContactoNoEncontradoException(idPaciente))
        );
    }
}
