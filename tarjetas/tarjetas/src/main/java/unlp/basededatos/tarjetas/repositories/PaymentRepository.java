package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	@Query("from Payment where code = :code")
    List<Payment> findPaymentsByCode(@Param("code") String code);
	
    @Query(value = "select sum(payment.purchase) "
    		+ "FROM payment "
    		+ "INNER JOIN cash_payment "
    		+ "ON payment.id_payment = cash_payment.id_payment "
    		+ "WHERE payment.`month` = :month "
    		+ "GROUP by payment.`month` "
    		+ "ORDER by payment.`month` ", nativeQuery = true)
    float totalCashByMonth(@Param("month") String month);

}
