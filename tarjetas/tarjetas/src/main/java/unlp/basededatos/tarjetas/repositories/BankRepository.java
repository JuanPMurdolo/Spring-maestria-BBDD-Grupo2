package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface BankRepository extends JpaRepository<Bank, Long>{

	@Query(value = "SELECT sum(payment.purchase) AS total_importe, bank.id_bank, card.id_card "
			+ " FROM payment "
			+ "	INNER JOIN payment_cashpayments "
			+ "	ON payment.id_payment = payment_cashpayments.payment_id_payment "
			+ "	INNER JOIN cash_payment "
			+ "	ON  payment_cashpayments.cashpayments_id_purchase = cash_payment.id_purchase "
			+ "	INNER JOIN card ON  cash_payment.id_card = card.id_card "
			+ "	INNER JOIN bank	ON  card.id_bank = bank.id_bank "
			+ " GROUP BY bank.id_bank, card.id_card "
			+ " ORDER BY total_importe DESC "
			+ " LIMIT 1 ", nativeQuery = true)
    public Map<String, Object> findBankMostImportCashByCard();
	
	@Query(value = "SELECT sum(payment.purchase) AS total_importe, bank.id_bank, card.id_card "
			+ " FROM payment"
			+ "	INNER JOIN 	quota"
			+ "	INNER JOIN 	monthly_payments_quotas"
			+ "	ON  quota.id_quota = monthly_payments_quotas.quotas_id_quota"
			+ "	INNER JOIN 	monthly_payments ON  monthly_payments_quotas.monthly_payments_id_purchase = monthly_payments.id_purchase "
			+ "	INNER JOIN card  ON  monthly_payments.id_card = card.id_card "
			+ "	INNER JOIN bank ON  card.id_bank = bank.id_bank "
			+ "	INNER JOIN payment_quotas_paid "
			+ "	ON  payment.id_payment = payment_quotas_paid.payment_id_payment AND quota.id_quota = payment_quotas_paid.quotas_paid_id_quota"
			+ " GROUP BY bank.id_bank, card.id_card "
			+ " ORDER BY total_importe DESC "
			+ " LIMIT 1 ", nativeQuery = true)
    public Map<String, Object> findBankMostImportMonthlyByCard();
}
