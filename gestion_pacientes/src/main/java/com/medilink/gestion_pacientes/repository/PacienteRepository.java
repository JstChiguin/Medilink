package com.medilink.gestion_pacientes.repository;


import com.medilink.gestion_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByNumeroDocumento(String numeroDocumento);
}
