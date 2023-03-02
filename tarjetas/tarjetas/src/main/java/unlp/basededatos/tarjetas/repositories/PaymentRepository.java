package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, Long> {

	@Query("from Payment where code = :code")
    List<Payment> findPaymentsByCode(@Param("code") String code);
	
    @Query(value = "SELECT sum(payment.purchase) "
    		+ "FROM payment "
    		+ "INNER JOIN payment_cashpayments "
    		+ "ON  payment.id_payment = payment_cashpayments.payment_id_payment "
    		+ "INNER JOIN cash_payment "
    		+ "ON  payment_cashpayments.cashpayments_id_purchase = cash_payment.id_purchase "
    		+ "WHERE payment.`month` = :month ", nativeQuery = true)
    float totalCashByMonth(@Param("month") String month);

    @Query(value = "SELECT sum(payment.purchase) "
    		+ "FROM payment "
    		+ "INNER JOIN payment_quotas "
    		+ "ON	payment.id_payment = payment_quotas.payment_id_payment "
    		+ "INNER JOIN quota "
    		+ "ON  payment_quotas.quotas_id_quota = quota.id_quota "
    		+ "WHERE payment.`month` = :month ", nativeQuery = true)
    float totalQuotasByMonth(@Param("month") String month);

}
