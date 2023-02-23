package unlp.basededatos.tarjetas.repositories.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.MonthlyPayments;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IBankRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class BankRepositoryImpl implements IBankRepository {
	
	@Autowired
	private BankRepository repository;

	@Override
	@Transactional
	public Bank getBankMostImportByCard() throws TarjetasException {
		try {

			Long id_bank_most = null;

			Map<String, Object> totalMonthly = getBankMostImportCashByCard();
			Map<String, Object> totalCash = getBankMostImportMonthlyByCard();

			totalMonthly.forEach((key, value) -> System.out.println(key + ":" + value));
			System.out.println("The id_card mensual is: " + totalMonthly.get("id_card"));
			System.out.println("The id_card cash is: " + totalCash.get("id_card"));

			float totalCardMonthly = Float.parseFloat(totalMonthly.get("total_importe").toString());
			float totalCardCash = Float.parseFloat(totalCash.get("total_importe").toString());
			System.out.printf("%.2f", totalCardMonthly);
			System.out.printf("%.2f", totalCardCash);

			totalCash.forEach((key, value) -> System.out.println(key + ":" + value));

			if (Float.compare(totalCardMonthly, totalCardCash) == 0) {

				System.out.println("f1=f2");
			} else if (Float.compare(totalCardMonthly, totalCardCash) < 0) {

				System.out.println("totalCardMonthly<totalCardCash");
				id_bank_most = Long.parseLong(totalCash.get("id_bank").toString());
			} else {

				System.out.println("ftotalCardMonthly>totalCardCash");
				id_bank_most = Long.parseLong(totalMonthly.get("id_bank").toString());

			}

			Bank bank1 = this.repository.findById(id_bank_most)
					.orElseThrow(() -> new EntityNotFoundException("Can't find bank by id: 1"));

			return bank1;
		} catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getBankMostImportCashByCard() {
        return repository.findBankMostImportCashByCard();
	}

	@Override
	public Map<String, Object> getBankMostImportMonthlyByCard() {
        return repository.findBankMostImportMonthlyByCard();

	}

	public void save(Bank bank) {
		this.repository.save(bank);
	}

	public Optional<Bank> findById(Long id) {
        return this.repository.findById(id);
	}
}
