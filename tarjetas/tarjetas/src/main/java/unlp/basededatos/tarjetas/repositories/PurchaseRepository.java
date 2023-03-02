package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, Long> {

    @Query(value = "SELECT cash_payment.cuit_store, cash_payment.amount, cash_payment.store " +
            "FROM cash_payment " +
            "INNER JOIN payment_cashpayments ON cash_payment.id_purchase = payment_cashpayments.cashpayments_id_purchase " +
            "INNER JOIN payment ON payment_cashpayments.payment_id_payment = payment.id_payment " +
            "WHERE payment.`month` = :month " +
            "GROUP BY cash_payment.store " +
            "ORDER BY cash_payment.amount DESC " +
            "LIMIT 1", nativeQuery = true)
    String getStoreWithMoreSalesCash(@Param("month") String month);

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
