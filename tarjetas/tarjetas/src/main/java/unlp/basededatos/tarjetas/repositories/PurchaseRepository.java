package unlp.basededatos.tarjetas.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.BankDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	/*
	 * @Query(value =
	 * "SELECT cashpayment.cuit_store, cashpayment.amount, cashpayment.store   " +
	 * "            FROM cashpayment   " +
	 * "            INNER JOIN payment_cashpayment ON cashpayment.id = payment_cashpayment.cashpayment_id   "
	 * +
	 * "            INNER JOIN payment ON payment_cashpayment.payment_id = payment.id   "
	 * + "            WHERE payment.`month` = :month  " +
	 * "            GROUP BY cashpayment.cuit_store, cashpayment.amount, cashpayment.store   "
	 * + "            ORDER BY cashpayment.amount DESC   " + "            LIMIT 1",
	 * nativeQuery = true) String getStoreWithMoreSalesCash(@Param("month") String
	 * month);
	 */

    //la SQL de arriba pasada a HQL
    @Query(value = "SELECT c.cuitStore as ciut, c.amount  as amount, c.store as store "
	    		+ " FROM Payment p "
	    		+ " INNER JOIN p.cashpayment c   "
	    		+ " WHERE p.month   = :month    "
	    		+ " GROUP BY c.cuitStore, c.amount ,c.store "
	    		+ " ORDER BY c.amount DESC   ")
    List<PurchaseDTO> findStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);
    
	/*
	 * @Query(value =
	 * "SELECT monthly_payments.store,monthly_payments.cuit_store,monthly_payments.amount "
	 * + "FROM monthly_payments " + "INNER JOIN payment " +
	 * "INNER JOIN quota ON monthly_payments.id_purchase = quota.montlypayment_id "
	 * +
	 * "INNER JOIN payment_quotas ON payment.id_payment = payment_quotas.payment_id_payment "
	 * + "AND quota.id_quota = payment_quotas.quotas_id_quota " +
	 * "WHERE payment.`month` = :month " + "GROUP BY monthly_payments.cuit_store " +
	 * "ORDER BY monthly_payments.amount DESC " + "LIMIT 1", nativeQuery = true)
	 * String getStoreWithMoreSalesMonthly(@Param("month") String month);
	 */

    //la SQL de arriba pasada a HQL
    @Query(value = "SELECT m.cuitStore as ciut, m.amount as amount, m.store  as store "
    		+ "FROM Payment p, MonthlyPayments m "
    		+ "INNER JOIN p.quota "
    		+ " WHERE p.month   = :month   "
    		+ "GROUP BY m.cuitStore, m.amount ,m.store "
    		+ "ORDER BY m.amount DESC  ")
    List<PurchaseDTO> findStoreWithMoreSalesMonthly(@Param("month") String month, Pageable pageable);
    
	/*
	 * @Query(value = "SELECT cashpayment.*, monthlypayments.*  " +
	 * "     FROM monthlypayments,cashpayment  " +
	 * "     WHERE monthlypayments.id = :id OR cashpayment.id = :id ", nativeQuery =
	 * true) String getPurchaseInfo(@Param("id") Long id);
	 */
}
