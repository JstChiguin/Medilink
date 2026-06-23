package com.medilink.gestion_pacientes.controller;

import com.medilink.gestion_pacientes.dto.request.PacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PacienteResponse;
import com.medilink.gestion_pacientes.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@Tag(name = "Paciente", description = "Operaciones relacionadas con el paciente.")

public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    @Operation(summary = "Obtener a los pacientes", description = "Obtiene una lista del  de todos los pacientes.")
    public ResponseEntity<List<PacienteResponse>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{idPaciente}")
    @Operation(summary = "Obtener al paciente por su Id", description = "Obtiene una lista de la infromacion de un paciente a través de su Id")
    public ResponseEntity<PacienteResponse> obtenerPacientePorId(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(pacienteService.buscarPorId(idPaciente));
    }

    @PostMapping
    @Operation(summary = "Crear un paciente", description = "Obtiene una lista del contacto de todos los pacientes.")
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest pacienteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crearPaciente(pacienteRequest));
    }

    @PutMapping("/{idPaciente}")
    @Operation(summary = "Actualizar un paciente ", description = "Actalizar informacion de un paciente a través de su Id.")
    public ResponseEntity<PacienteResponse> actualizarPaciente(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(idPaciente, pacienteRequest));
    }

    @PatchMapping("/{idPaciente}/desactivar")
    @Operation(summary = "Desactivar cuenta paciente", description = "Desactivar la cuenta de un paciente a través de su Id.")

    public ResponseEntity<String> desactivarPaciente(@PathVariable Long idPaciente) {
        pacienteService.desactivarPaciente(idPaciente);
        return ResponseEntity.ok("Paciente desactivado correctamente");
    }
}
