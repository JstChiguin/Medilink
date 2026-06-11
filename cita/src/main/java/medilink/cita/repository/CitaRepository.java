package medilink.cita.repository;

import medilink.cita.model.entity.Cita;
import medilink.cita.model.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Verifica si ya existe una cita en esa fecha y hora
    boolean existsByFechaCitaAndHoraCita(LocalDate fechaCita, LocalTime horaCita);

    // Buscar citas por estado
    List<Cita> findByEstadoCita(EstadoCita estadoCita);
}
