package com.medilink.gestion_pacientes.controller;


import com.medilink.gestion_pacientes.dto.request.PerfilPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PerfilPacienteResponse;
import com.medilink.gestion_pacientes.service.PerfilPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
public class PerfilPacienteController {

    private final PerfilPacienteService perfilPacienteService;

    @GetMapping
    public ResponseEntity<List<PerfilPacienteResponse>> listarPerfiles() {
        return ResponseEntity.ok(perfilPacienteService.listarPerfiles());
    }
    @PostMapping("/{idPaciente}")
    public ResponseEntity<PerfilPacienteResponse> crearPerfil(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PerfilPacienteRequest perfilPacienteRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(perfilPacienteService.crearPerfil(idPaciente, perfilPacienteRequest));
    }

    @PutMapping("/paciente/{idPaciente}")
    public ResponseEntity<PerfilPacienteResponse> actualizarPerfil(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PerfilPacienteRequest perfilPacienteRequest) {
        return ResponseEntity.ok(perfilPacienteService.actualizarPerfil(idPaciente, perfilPacienteRequest));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<PerfilPacienteResponse> obtenerPerfilPorPaciente(
            @PathVariable Long idPaciente) {
        return ResponseEntity.ok(perfilPacienteService.obtenerPerfilPorPaciente(idPaciente));
    }
}
