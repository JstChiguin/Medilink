package com.medilink.gestion_pacientes.controller;

import com.medilink.gestion_pacientes.dto.request.ContactoPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.ContactoPacienteResponse;
import com.medilink.gestion_pacientes.service.ContactoPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
public class ContactoPacienteController {

    private final ContactoPacienteService contactoPacienteService;

    @PostMapping
    public ResponseEntity<ContactoPacienteResponse> crearContacto(
            @Valid @RequestBody ContactoPacienteRequest contactoPacienteRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contactoPacienteService.crearContacto(contactoPacienteRequest));
    }

    @PutMapping("/paciente/{idPaciente}")
    public ResponseEntity<ContactoPacienteResponse> actualizarContacto(
            @PathVariable Long idPaciente,
            @Valid @RequestBody ContactoPacienteRequest contactoPacienteRequest) {
        return ResponseEntity.ok(contactoPacienteService.actualizarContacto(idPaciente, contactoPacienteRequest));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<ContactoPacienteResponse> obtenerContactoPorPaciente(
            @PathVariable Long idPaciente) {
        return ResponseEntity.ok(contactoPacienteService.obtenerContactoPorPaciente(idPaciente));
    }
}
