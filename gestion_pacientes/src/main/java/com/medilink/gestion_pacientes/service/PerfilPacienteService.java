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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilPacienteService {

    private final PerfilPacienteRepository perfilPacienteRepository;
    private final PacienteRepository pacienteRepository;
    private final ContactoPacienteRepository contactoPacienteRepository;
    private final PerfilPacienteMapper perfilPacienteMapper;

    public List<PerfilPacienteResponse> listarPerfiles() {
        return perfilPacienteRepository.findAll()
                .stream()
                .map(perfilPacienteMapper::toResponse)
                .toList();
    }

    public PerfilPacienteResponse crearPerfil(Long idPaciente, PerfilPacienteRequest req) {
        Paciente paciente = pacienteRepository
                .findById(idPaciente)
                .orElseThrow(() -> new PacienteNoEncontradoException(idPaciente));

        ContactoPaciente contacto = contactoPacienteRepository
                .findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new ContactoNoEncontradoException(idPaciente));

        PerfilPaciente perfil = perfilPacienteMapper.toEntity(req);
        perfil.setPaciente(paciente);
        perfil.setContactoPaciente(contacto);
        perfil.setUltimaActualizacion(LocalDateTime.now());

        return perfilPacienteMapper.toResponse(perfilPacienteRepository.save(perfil));
    }

    public PerfilPacienteResponse actualizarPerfil(Long idPaciente, PerfilPacienteRequest req) {
        PerfilPaciente perfilExistente = perfilPacienteRepository
                .findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new PerfilNoEncontradoException(idPaciente));

        perfilExistente.setAntecedentesMedicos(req.getAntecedentesMedicos());
        perfilExistente.setAlergias(req.getAlergias());
        perfilExistente.setMedicamentosActuales(req.getMedicamentosActuales());
        perfilExistente.setInformacionRelevante(req.getInformacionRelevante());
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