package com.medilink.gestion_pacientes.repository;


import com.medilink.gestion_pacientes.model.PerfilPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilPacienteRepository extends JpaRepository<PerfilPaciente, Long> {
    Optional<PerfilPaciente> findByPaciente_IdPaciente(Long idPaciente);
    boolean existsByPaciente_IdPaciente(Long idPaciente);
}
