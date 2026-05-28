package com.medilink.gestion_pacientes.service;


import com.medilink.gestion_pacientes.dto.request.PerfilPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PerfilPacienteResponse;
import com.medilink.gestion_pacientes.exception.ContactoNoEncontradoException;
import com.medilink.gestion_pacientes.exception.PacienteNoEncontradoException;
import com.medilink.gestion_pacientes.exception.PerfilNoEncontradoException;
import com.medilink.gestion_pacientes.mapper.PerfilPacienteMapper;
import com.medilink.gestion_pacientes.model.ContactoPaciente;
import com.medilink.gestion_pacientes.model.Paciente;
import com.medilink.gestion_pacientes.model.PerfilPaciente;
import com.medilink.gestion_pacientes.repository.ContactoPacienteRepository;
import com.medilink.gestion_pacientes.repository.PacienteRepository;
import com.medilink.gestion_pacientes.repository.PerfilPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PerfilPacienteService {

    private final PerfilPacienteRepository perfilPacienteRepository;
    private final PacienteRepository pacienteRepository;
    private final ContactoPacienteRepository contactoPacienteRepository;
    private final PerfilPacienteMapper perfilPacienteMapper;

    public PerfilPacienteResponse crearPerfil(PerfilPacienteRequest perfilPacienteRequest) {
        Paciente paciente = pacienteRepository
                .findById(perfilPacienteRequest.getIdPaciente())
                .orElseThrow(() -> new PacienteNoEncontradoException(perfilPacienteRequest.getIdPaciente()));

        ContactoPaciente contacto = contactoPacienteRepository
                .findById(perfilPacienteRequest.getIdContacto())
                .orElseThrow(() -> new ContactoNoEncontradoException(perfilPacienteRequest.getIdPaciente()));

        PerfilPaciente perfil = perfilPacienteMapper.toEntity(perfilPacienteRequest);
        perfil.setPaciente(paciente);
        perfil.setContactoPaciente(contacto);
        perfil.setUltimaActualizacion(LocalDateTime.now());

        return perfilPacienteMapper.toResponse(perfilPacienteRepository.save(perfil));
    }

    public PerfilPacienteResponse actualizarPerfil(Long idPaciente, PerfilPacienteRequest perfilPacienteRequest) {
        PerfilPaciente perfilExistente = perfilPacienteRepository
                .findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new PerfilNoEncontradoException(idPaciente));

        ContactoPaciente contacto = contactoPacienteRepository
                .findById(perfilPacienteRequest.getIdContacto())
                .orElseThrow(() -> new ContactoNoEncontradoException(idPaciente));

        perfilExistente.setAntecedentesMedicos(perfilPacienteRequest.getAntecedentesMedicos());
        perfilExistente.setAlergias(perfilPacienteRequest.getAlergias());
        perfilExistente.setMedicamentosActuales(perfilPacienteRequest.getMedicamentosActuales());
        perfilExistente.setInformacionRelevante(perfilPacienteRequest.getInformacionRelevante());
        perfilExistente.setContactoPaciente(contacto);
        perfilExistente.setUltimaActualizacion(LocalDateTime.now());

        return perfilPacienteMapper.toResponse(perfilPacienteRepository.save(perfilExistente));
    }

    public PerfilPacienteResponse obtenerPerfilPorPaciente(Long idPaciente) {
        return perfilPacienteMapper.toResponse(
                perfilPacienteRepository
                        .findByPaciente_IdPaciente(idPaciente)
                        .orElseThrow(() -> new PerfilNoEncontradoException(idPaciente))
        );
    }
}
