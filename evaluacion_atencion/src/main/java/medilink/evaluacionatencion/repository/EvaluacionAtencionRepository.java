package medilink.evaluacionatencion.repository;

import medilink.evaluacionatencion.model.entity.EvaluacionAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionAtencionRepository
        extends JpaRepository<EvaluacionAtencion, Long> {

    List<EvaluacionAtencion> findByIdPaciente(Long idPaciente);

    List<EvaluacionAtencion> findByIdProfesional(Long idProfesional);

    List<EvaluacionAtencion> findByIdCita(Long idCita);

    List<EvaluacionAtencion> findByCalificacion(Integer calificacion);
}