package com.medilink.gestion_pacientes.controller;

import com.medilink.gestion_pacientes.dto.request.ContactoPacienteRequest;
import com.medilink.gestion_pacientes.dto.response.ContactoPacienteResponse;
import com.medilink.gestion_pacientes.service.ContactoPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
@Tag(name = "Cotacto del paciente", description = "Operaciones relacionadas con el contacto personal y de emergenca del paciente.")

public class ContactoPacienteController {

    private final ContactoPacienteService contactoPacienteService;
    @GetMapping
    @Operation(summary = "Obtener el contacto de todos los pacientes", description = "Obtiene una lista del contacto de todos los pacientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista Obtenida Exitosamente")
    } )
    public ResponseEntity<List<ContactoPacienteResponse>> listarContactos() {
        return ResponseEntity.ok(contactoPacienteService.listarContactos());
    }

    @PostMapping
    @Operation(summary = "Crear contacto del pactiente", description = "Crea el contacto personal y de emergencia de un paciente")
    public ResponseEntity<ContactoPacienteResponse> crearContacto(
            @Valid @RequestBody ContactoPacienteRequest contactoPacienteRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contactoPacienteService.crearContacto(contactoPacienteRequest));
    }

    @PutMapping("/paciente/{idPaciente}")
    @Operation(summary = "Acualizar contacto por IdPacinte", description = "Actualiza el contacto del paciente a través del Id del paciente.")
    public ResponseEntity<ContactoPacienteResponse> actualizarContacto(
            @PathVariable Long idPaciente,
            @Valid @RequestBody ContactoPacienteRequest contactoPacienteRequest) {
        return ResponseEntity.ok(contactoPacienteService.actualizarContacto(idPaciente, contactoPacienteRequest));
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Obtener contacto del paciente por IdPaciente", description = "Obtiene toda la informacion de contacto de un paciete a través de su Id como paciente")
    public ResponseEntity<ContactoPacienteResponse> obtenerContactoPorPaciente(
            @PathVariable Long idPaciente) {
        return ResponseEntity.ok(contactoPacienteService.obtenerContactoPorPaciente(idPaciente));
    }
}
