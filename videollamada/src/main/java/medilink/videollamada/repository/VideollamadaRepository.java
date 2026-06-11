package medilink.videollamada.repository;

import medilink.videollamada.model.entity.Videollamada;
import medilink.videollamada.model.enums.EstadoVideollamada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideollamadaRepository extends JpaRepository<Videollamada, Long> {

    List<Videollamada> findByIdPaciente(Long idPaciente);

    List<Videollamada> findByIdProfesional(Long idProfesional);

    List<Videollamada> findByIdCita(Long idCita);

    List<Videollamada> findByEstadoVideollamada(EstadoVideollamada estadoVideollamada);
}