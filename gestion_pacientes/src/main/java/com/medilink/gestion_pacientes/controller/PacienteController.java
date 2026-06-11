package com.medilink.gestion_pacientes.controller;

import com.medilink.gestion_pacientes.dto.request.PacienteRequest;
import com.medilink.gestion_pacientes.dto.response.PacienteResponse;
import com.medilink.gestion_pacientes.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> obtenerPacientePorId(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(pacienteService.buscarPorId(idPaciente));
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest pacienteRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crearPaciente(pacienteRequest));
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> actualizarPaciente(
            @PathVariable Long idPaciente,
            @Valid @RequestBody PacienteRequest pacienteRequest) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(idPaciente, pacienteRequest));
    }

    @PatchMapping("/{idPaciente}/desactivar")
    public ResponseEntity<String> desactivarPaciente(@PathVariable Long idPaciente) {
        pacienteService.desactivarPaciente(idPaciente);
        return ResponseEntity.ok("Paciente desactivado correctamente");
    }
}
