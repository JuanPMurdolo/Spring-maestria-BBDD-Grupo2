package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.BankDTO;
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
	
	/*
	 * @Query(value = "SELECT sum(p.purchase) as total" + " FROM Payment p  " +
	 * " INNER JOIN p.cashpayment   " + " WHERE p.month  = :month ") float
	 * totalCashByMonth(@Param("month") String month);
	 */
	/*
	 * @Query(value = "SELECT sum(p.purchase) as total" + " FROM Payment p  " +
	 * " INNER JOIN p.cashpayment   " + " WHERE p.month  = :month ") List<ArrayList>
	 * totalCashByMonth2(@Param("month") String month);
	 */

	@Aggregation(pipeline = {
			"{$match: { month: ?0}}",
			"{$lookup: {from : 'cashpayment',localField :'cashpayment.$id' ,foreignField : '_id', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$group: { _id: '', total: {$sum: '$purchase'}}}"
		})
    List<ArrayList>  totalCashByMonth(@Param("month") String month);
	


	/*
	 * @Query(value = "SELECT sum(payment.purchase) " + "FROM payment " +
	 * "INNER JOIN payment_quotas " +
	 * "ON	payment.id_payment = payment_quotas.payment_id_payment " +
	 * "INNER JOIN quota " + "ON  payment_quotas.quotas_id_quota = quota.id_quota "
	 * + "WHERE payment.`month` = :month ", nativeQuery = true) float
	 * totalQuotasByMonth(@Param("month") String month);
	 */

	//la SQL de arriba pasada a HQL
	/*
	 * @Query(value = "SELECT sum(p.purchase) " + " FROM Payment p  " +
	 * " INNER JOIN p.quota " + " WHERE p.month  = :month ") float
	 * totalQuotasByMonth(@Param("month") String month);
	 */
    
	@Aggregation(pipeline = {
			"{$match: { month: ?0}}",
			"{$lookup: {from : 'monthlypayments',localField :'monthlypayments.quota.id' ,foreignField : 'quota', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$group: { _id: '', total: {$sum: '$purchase'}}}"
		})
	List<ArrayList> totalQuotasByMonth(String month);
	
	@Aggregation(pipeline = {
			"{$match: { month: ?0}}",
			"{$lookup: {from : 'monthlypayments',localField :'monthlypayments.quota.id' ,foreignField : 'quota', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$group: { _id: '', total: {$sum: '$purchase'}}}"
		})
	List<ArrayList> totalCashByMonth2(String month);
	
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
    List<ArrayList>  findStoreWithMoreSalesCash2(@Param("month") String month, Pageable pageable);

	
	@Query("{ {'month': ?0}, {'cashpayment.cuitStore': 1, 'cashpayment.amount': 1,'cashpayment.store': 1 }}")
    List<ArrayList>  findStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);
	
	/*
	 * @Query(value =
	 * "SELECT m.cuitStore as ciut, m.amount as amount, m.store  as store " +
	 * "FROM Payment p, MonthlyPayments m " + "INNER JOIN p.quota " +
	 * " WHERE p.month   = :month   " + "GROUP BY m.cuitStore, m.amount ,m.store " +
	 * "ORDER BY m.amount DESC  ") List<PurchaseDTO>
	 * findStoreWithMoreSalesMonthly(@Param("month") String month, Pageable
	 * pageable);
	 */
    
	@Aggregation(pipeline = {
			"{$match: { month: ?0}}",
			"{$lookup: {from : 'quota',localField :'quota.$id' ,foreignField : '_id', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$unwind: '$reporter.quota'}",
			"{$lookup: {from : 'monthlypayments',localField :'monthlypayments.quota.id' ,foreignField : 'quota', as : 'mensuales'}}",
			"{$unwind: '$mensuales'}",
			"{$sort: { 'mensuales.amount': -1 }}",
	        "{$project: {  'result': ['$mensuales.cuitStore','$mensuales.amount', '$mensuales.store']}}"
		})
    List<ArrayList>  findStoreWithMoreSalesMonthly(@Param("month") String month, Pageable pageable);

	
//	@Query(value = "SELECT sum(p.purchase) as total, b.id as bank, c.id as card "
//			+ "FROM Payment p  "
//			+ "INNER JOIN p.cashpayment t "
//			+ "INNER JOIN t.card c "
//			+ "INNER JOIN c.bank b "
//			+ "GROUP BY b.id, c.id "
//			+ "ORDER BY sum(p.purchase) DESC ")
//    public List<BankDTO> findBankMostImportCashByCard(Pageable pageable);
//	
	@Aggregation(pipeline = {
			"{$lookup: {from : 'cashpayment',localField :'cashpayment.$id' ,foreignField : '_id', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$lookup: {from : 'card',localField :'card.id' ,foreignField : 'reporter.card', as : 'nivel2'}}",
			"{$unwind: '$nivel2'}",
			"{$lookup: {from : 'bank',localField :'bank.id' ,foreignField : 'nivel2.bank', as : 'nivel3'}}",
			"{$unwind: '$nivel3'}",
			"{$sort: { 'purchase': -1 }}",
	        "{$project: {  'result': ['$purchase', '$nivel3._id']}}"
		})
    public List<ArrayList> findBankMostImportCashByCard(Pageable pageable);
	
//	
//	@Query(value = "SELECT sum(p.purchase) as total, b.id as bank, c.id as card  "
//			+ "FROM Payment p, MonthlyPayments m, Quota q "
//			+ "INNER JOIN p.quota j "
//			+ "INNER JOIN m.card c "
//			+ "INNER JOIN c.bank b "
//			+ "WHERE j.id = q.id  "
//			+ "GROUP BY b.id, c.id "
//			+ "ORDER BY sum(p.purchase) DESC  ")
//    public List<BankDTO> findBankMostImportMonthlyByCard(Pageable pageable);
	
	@Aggregation(pipeline = {
			"{$lookup: {from : 'quota',localField :'quota.$id' ,foreignField : '_id', as : 'reporter'}}",
			"{$unwind: '$reporter'}",
			"{$unwind: '$reporter.quota'}",
			"{$lookup: {from : 'monthlypayments',localField :'monthlypayments.quota.id' ,foreignField : 'quota', as : 'mensuales'}}",
			"{$unwind: '$mensuales'}",
			"{$sort: { 'mensuales.amount': -1 }}",
	        "{$project: {  'result': ['$mensuales.cuitStore','$mensuales.amount', '$mensuales.store']}}"
		})
    public List<ArrayList> findBankMostImportMonthlyByCard(Pageable pageable);
    
}
