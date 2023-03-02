package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.utils.BankDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BankRepository extends MongoRepository<Bank, Long> {

	/*
	 * @Query(value =
	 * "SELECT sum(payment.purchase) AS total_importe, bank.id_bank, card.id_card "
	 * + " FROM payment " + "	INNER JOIN payment_cashpayments " +
	 * "	ON payment.id_payment = payment_cashpayments.payment_id_payment " +
	 * "	INNER JOIN cash_payment " +
	 * "	ON  payment_cashpayments.cashpayments_id_purchase = cash_payment.id_purchase "
	 * + "	INNER JOIN card ON  cash_payment.id_card = card.id_card " +
	 * "	INNER JOIN bank	ON  card.id_bank = bank.id_bank " +
	 * " GROUP BY bank.id_bank, card.id_card " + " ORDER BY total_importe DESC " +
	 * " LIMIT 1 ", nativeQuery = true) public Map<String, Object>
	 * findBankMostImportCashByCard();
	 */
	
	//la SQL de arriba pasada a HQL
	@Query(value = "SELECT sum(p.purchase) as total, b.id as bank, c.id as card "
			+ "FROM Payment p  "
			+ "INNER JOIN p.cashpayment t "
			+ "INNER JOIN t.card c "
			+ "INNER JOIN c.bank b "
			+ "GROUP BY b.id, c.id "
			+ "ORDER BY sum(p.purchase) DESC ")
    public List<BankDTO> findBankMostImportCashByCard(Pageable pageable);
	
	/*
	 * @Query(value =
	 * "SELECT sum(payment.purchase) AS total_importe, bank.id_bank, card.id_card "
	 * + " FROM payment" + "	INNER JOIN 	quota" +
	 * "	INNER JOIN 	monthly_payments_quotas" +
	 * "	ON  quota.id_quota = monthly_payments_quotas.quotas_id_quota" +
	 * "	INNER JOIN 	monthly_payments ON  monthly_payments_quotas.monthly_payments_id_purchase = monthly_payments.id_purchase "
	 * + "	INNER JOIN card  ON  monthly_payments.id_card = card.id_card " +
	 * "	INNER JOIN bank ON  card.id_bank = bank.id_bank " +
	 * "	INNER JOIN payment_quotas_paid " +
	 * "	ON  payment.id_payment = payment_quotas_paid.payment_id_payment AND quota.id_quota = payment_quotas_paid.quotas_paid_id_quota"
	 * + " GROUP BY bank.id_bank, card.id_card " + " ORDER BY total_importe DESC " +
	 * " LIMIT 1 ", nativeQuery = true) public Map<String, Object>
	 * findBankMostImportMonthlyByCard();
	 */
	
	//la SQL de arriba pasada a HQL
	@Query(value = "SELECT sum(p.purchase) as total, b.id as bank, c.id as card  "
			+ "FROM Payment p, MonthlyPayments m, Quota q "
			+ "INNER JOIN p.quota j "
			+ "INNER JOIN m.card c "
			+ "INNER JOIN c.bank b "
			+ "WHERE j.id = q.id  "
			+ "GROUP BY b.id, c.id "
			+ "ORDER BY sum(p.purchase) DESC  ")
    public List<BankDTO> findBankMostImportMonthlyByCard(Pageable pageable);
}
