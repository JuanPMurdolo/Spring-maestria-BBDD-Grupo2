package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	@Query("from Payment where code = :code")
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
	
    @Query(value = "SELECT sum(p.purchase) as total"
    		+ " FROM Payment p  "
    		+ " INNER JOIN p.cashpayment   "
    		+ " WHERE p.month  = :month ")
    float totalCashByMonth(@Param("month") String month);
    
    @Query(value = "SELECT sum(p.purchase) as total"
    		+ " FROM Payment p  "
    		+ " INNER JOIN p.cashpayment   "
    		+ " WHERE p.month  = :month ")
    List<PaymentDTO> totalCashByMonth2(@Param("month") String month);

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
    
	/*
	 * @Query(value =
	 * "SELECT c.cuitStore as cuit, c.amount  as amount, c.store as store " +
	 * " FROM Payment p " + " INNER JOIN p.cashpayment c   " +
	 * " WHERE p.month   = :month    " + " GROUP BY c.cuitStore, c.amount ,c.store "
	 * + " ORDER BY c.amount DESC   ") List<PurchaseDTO>
	 * findStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);
	 */

	//la HQL devuelve campos null no sabemos porque
    //la pase a query MongoDB y anda

	@Aggregation(pipeline = {
			"{$match: { month: ?0}}",
			"{$lookup: {from : 'cashpayment',localField :'cashpayment.$id' ,foreignField : '_id', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$sort: { 'reporter.amount': -1 }}",
	        "{$project: {  'result': ['$reporter.cuitStore','$reporter.amount', '$reporter.store']}}"
		})
    List<ArrayList>  findStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);


}
