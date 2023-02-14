package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	@Query("from Payment where code = :code")
    List<Payment> findPaymentsByCode(@Param("code") String code);

}
