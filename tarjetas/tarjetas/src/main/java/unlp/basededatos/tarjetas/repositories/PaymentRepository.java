package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	@Query(" from Payment "
		 + " where code = :code")
    List<Payment> findPaymentsByCode(@Param("code") String code);
	
	/*
	 * @Query(value = "SELECT sum(payment.purchase) " + "FROM payment " +
	 * "INNER JOIN payment_cashpayments " +
	 * "ON  payment.id_payment = payment_cashpayments.payment_id_payment " +
	 * "INNER JOIN cash_payment " +
	 * "ON  payment_cashpayments.cashpayments_id_purchase = cash_payment.id_purchase "
	 * + "WHERE payment.`month` = :month ", nativeQuery = true) float
	 * totalCashByMonth(@Param("month") String month);
	 */
	
	//la SQL de arriba pasada a HQL
    @Query(value = "SELECT sum(p.purchase) "
    		+ " FROM Payment p  "
    		+ " INNER JOIN p.cashpayment   "
    		+ " WHERE p.month  = :month ")
    float totalCashByMonth(@Param("month") String month);

	/*
	 * @Query(value = "SELECT sum(payment.purchase) " + "FROM payment " +
	 * "INNER JOIN payment_quotas " +
	 * "ON	payment.id_payment = payment_quotas.payment_id_payment " +
	 * "INNER JOIN quota " + "ON  payment_quotas.quotas_id_quota = quota.id_quota "
	 * + "WHERE payment.`month` = :month ", nativeQuery = true) float
	 * totalQuotasByMonth(@Param("month") String month);
	 */
    
	//la SQL de arriba pasada a HQL
    @Query(value = "SELECT sum(p.purchase) "
    		+ " FROM Payment p  "
    		+ " INNER JOIN p.quota "
    		+ " WHERE p.month  = :month ")
    float totalQuotasByMonth(@Param("month") String month);

}
