package com.medilink.gestion_pacientes.repository;

import com.medilink.gestion_pacientes.model.ContactoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactoPacienteRepository extends JpaRepository<ContactoPaciente, Long> {
    Optional<ContactoPaciente> findByPaciente_IdPaciente(Long idPaciente);
}
