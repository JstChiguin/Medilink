package com.medilink.gestion_pacientes.controller;


import com.medilink.gestion_pacientes.dto.request.PerfilPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PerfilPacienteResponse;
import com.medilink.gestion_pacientes.service.PerfilPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
@Tag(name = "Perfil del paciente", description = "Operaciones relacionadas con el  perfil medico paciente.")

public class PerfilPacienteController {

    private final PerfilPacienteService perfilPacienteService;

    @GetMapping
    @Operation(summary = "Obtener perfil de los pacientes", description = "Obtiene una lista del perfil de todos los pacientes.")
    public ResponseEntity<List<PerfilPacienteResponse>> listarPerfiles() {
        return ResponseEntity.ok(perfilPacienteService.listarPerfiles());
    }
    @PostMapping("/{idPaciente}")
    @Operation(summary = "Crear perfil del paciente por IdPaciente", description = "Crear un perfil para un paciente a través de su Id como Paciente")
    public ResponseEntity<PerfilPacienteResponse> crearPerfil(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PerfilPacienteRequest perfilPacienteRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(perfilPacienteService.crearPerfil(idPaciente, perfilPacienteRequest));
    }

    @PutMapping("/paciente/{idPaciente}")
    @Operation(summary = "Actualizar perfil del paciente por IdPaciente", description = "Actualizar un perfil para un paciente a través de su Id como paciente")
    public ResponseEntity<PerfilPacienteResponse> actualizarPerfil(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PerfilPacienteRequest perfilPacienteRequest) {
        return ResponseEntity.ok(perfilPacienteService.actualizarPerfil(idPaciente, perfilPacienteRequest));
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Obtener perfil de un paciente a través de IdPaciente", description = "Obtiene una lista del perfil de un paciente a través de su Id como paciente.")
    public ResponseEntity<PerfilPacienteResponse> obtenerPerfilPorPaciente(
            @PathVariable Long idPaciente) {
        return ResponseEntity.ok(perfilPacienteService.obtenerPerfilPorPaciente(idPaciente));
    }
}
