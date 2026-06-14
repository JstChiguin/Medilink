package medilink.agenda.repository;

import medilink.agenda.model.entity.AgendaMedica;
import medilink.agenda.model.enums.EstadoAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaMedica, Long> {

    boolean existsByIdProfesionalAndFechaAndHoraInicio(
            Long idProfesional,
            LocalDate fecha,
            LocalTime horaInicio
    );

    List<AgendaMedica> findByIdProfesional(Long idProfesional);

    List<AgendaMedica> findByEstado(EstadoAgenda estado);

    List<AgendaMedica> findByFecha(LocalDate fecha);
}