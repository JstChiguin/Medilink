package medilink.pago.repository;

import medilink.pago.model.entity.Pago;
import medilink.pago.model.enums.EstadoPago;
import medilink.pago.model.enums.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByIdPaciente(Long idPaciente);

    List<Pago> findByIdProfesional(Long idProfesional);

    List<Pago> findByIdCita(Long idCita);

    List<Pago> findByMetodoPago(MetodoPago metodoPago);

    List<Pago> findByEstadoPago(EstadoPago estadoPago);
}