package com.medilink.gestion_pacientes.service;

import com.medilink.gestion_pacientes.client.ClienteUsuario;
import com.medilink.gestion_pacientes.dto.request.PacienteRequest;
import com.medilink.gestion_pacientes.dto.response.UsuarioLimitado;
import com.medilink.gestion_pacientes.dto.response.UsuarioResponse;
import com.medilink.gestion_pacientes.exception.DocumentoDuplicadoException;
import com.medilink.gestion_pacientes.exception.PacienteNoEncontradoException;
import com.medilink.gestion_pacientes.exception.UsuarioNoEcontradoException;
import com.medilink.gestion_pacientes.mapper.PacienteMapper;
import com.medilink.gestion_pacientes.model.Paciente;
import com.medilink.gestion_pacientes.repository.PacienteRepository;
import com.medilink.gestion_pacientes.dto.response.PacienteResponse;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final ClienteUsuario clienteUsuario;

    public List<PacienteResponse> listarPacientes() {
        return pacienteMapper.toResponseList(pacienteRepository.findAll());
    }

    public PacienteResponse obtenerPacientePorId(Long idPaciente) {
        return pacienteMapper.toResponse(
                pacienteRepository.findById(idPaciente)
                        .orElseThrow(() -> new PacienteNoEncontradoException(idPaciente))
        );
    }
    public PacienteResponse buscarPorId(Long idPaciente) {
        //log.info("Lógica de negocio: Buscando paciente por ID: {}", idUsuario);
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() ->{
                    //log.warn("Lógica de negocio: No se encontró el producto con ID: {}", idUsuario);
                    return new PacienteNoEncontradoException(idPaciente);});

        PacienteResponse response = pacienteMapper.toResponse(paciente);

        try {
            //log.info("Feign Client: Solicitando categoría ID: {} a ms_catalogo", producto.getIdCategoria());
            UsuarioResponse usuarioResponse = clienteUsuario.obtenerUsuarioPorId(paciente.getIdUsuario());

            if (usuarioResponse == null) {
                //log.warn("Feign Client: La respuesta de la categoría fue nula");
                throw new PacienteNoEncontradoException(paciente.getIdUsuario());
            }
            UsuarioLimitado usuarioLimitado = UsuarioLimitado.builder()
                    .correoUsuario(usuarioResponse.getCorreoUsuario())

                    .build();
            response.setUsuario(usuarioLimitado);

        }
        catch(Exception e){
            //log.error("Feign Client: Falló la comunicación con el microservicio. Motivo: {}", e.getMessage());
            throw new UsuarioNoEcontradoException("Error de comunicacion"+ e.getMessage());
        }
        return response;
    }

    public PacienteResponse crearPaciente(PacienteRequest pacienteRequest) {

        if (pacienteRepository.existsByNumeroDocumento(pacienteRequest.getNumeroDocumento())) {
            throw new DocumentoDuplicadoException(pacienteRequest.getNumeroDocumento());
        }

        Paciente paciente = pacienteMapper.toEntity(pacienteRequest);
        return pacienteMapper.toResponse(pacienteRepository.save(paciente));
    }

    public PacienteResponse actualizarPaciente(Long idPaciente, PacienteRequest pacienteRequest) {
        Paciente pacienteExistente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new PacienteNoEncontradoException(idPaciente));

        pacienteExistente.setNumeroDocumento(pacienteRequest.getNumeroDocumento());
        pacienteExistente.setNombreCompletoPaciente(pacienteRequest.getNombreCompletoPaciente());
        pacienteExistente.setFechaNacimiento(pacienteRequest.getFechaNacimiento());
        pacienteExistente.setGenero(pacienteRequest.getGenero());
        pacienteExistente.setEstadoActivo(pacienteRequest.getEstadoActivo());
        pacienteExistente.setFechaRegistro(pacienteRequest.getFechaRegistro());

        return pacienteMapper.toResponse(pacienteRepository.save(pacienteExistente));
    }

    public void desactivarPaciente(Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new PacienteNoEncontradoException(idPaciente));

        paciente.setEstadoActivo(false);
        pacienteRepository.save(paciente);
    }
}
