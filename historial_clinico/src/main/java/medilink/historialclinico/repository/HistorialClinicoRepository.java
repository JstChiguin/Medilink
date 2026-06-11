package medilink.historialclinico.repository;

import medilink.historialclinico.model.entity.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {

    // Buscar historial clínico por paciente
    List<HistorialClinico> findByIdPaciente(Long idPaciente);

    // Buscar historial clínico por profesional
    List<HistorialClinico> findByIdProfesional(Long idProfesional);

}