package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Purchase;

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
    @Query(value = "SELECT c.cuitStore, c.amount ,c.store "
	    		+ " FROM Payment p "
	    		+ " INNER JOIN p.cashpayment c   "
	    		+ " WHERE p.month   = :month    "
	    		+ " GROUP BY c.cuitStore, c.amount ,c.store "
	    		+ " ORDER BY c.amount DESC   ")
    String getStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);
    
    @Query(value = "SELECT monthly_payments.store,monthly_payments.cuit_store,monthly_payments.amount " +
            "FROM monthly_payments " +
            "INNER JOIN payment " +
            "INNER JOIN quota ON monthly_payments.id_purchase = quota.montlypayment_id " +
            "INNER JOIN payment_quotas ON payment.id_payment = payment_quotas.payment_id_payment " +
            "AND quota.id_quota = payment_quotas.quotas_id_quota " +
            "WHERE payment.`month` = :month " +
            "GROUP BY monthly_payments.cuit_store " +
            "ORDER BY monthly_payments.amount DESC " +
            "LIMIT 1", nativeQuery = true)
    String getStoreWithMoreSalesMonthly(@Param("month") String month);

    @Query(value = "SELECT cash_payment.*, monthly_payments.* " +
            "FROM monthly_payments,cash_payment " +
            "WHERE monthly_payments.id_purchase = :id OR cash_payment.id_purchase = :id", nativeQuery = true)
    String getPurchaseInfo(@Param("id") Long id);
}
