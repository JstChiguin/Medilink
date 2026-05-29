package medilink.recetamedica.repository;

import medilink.recetamedica.model.entity.RecetaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaMedicaRepository extends JpaRepository<RecetaMedica, Long> {

    List<RecetaMedica> findByIdPaciente(Long idPaciente);

    List<RecetaMedica> findByIdProfesional(Long idProfesional);

    List<RecetaMedica> findByIdCita(Long idCita);
}