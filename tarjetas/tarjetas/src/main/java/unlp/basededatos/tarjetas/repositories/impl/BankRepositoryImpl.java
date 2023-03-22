package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IBankRepository;
import unlp.basededatos.tarjetas.utils.BankDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class BankRepositoryImpl implements IBankRepository {
	
	@Autowired
	private BankRepository repository;

	@Autowired
	private PaymentRepository repositoryPay;
	
	@Override
	public Bank getBankMostImportByCard() throws TarjetasException {
		try {

			String id_bank_most = null;
	        Pageable paging = PageRequest.of(0, 1);

			List<ArrayList> totalMonthly = getBankMostImportCashByCard(paging);
			List<ArrayList> totalCash = getBankMostImportMonthlyByCard(paging);

			System.out.println(totalMonthly.toArray().toString()); 
			System.out.println(totalCash.toString()); 
					
			String[] arrMont = totalMonthly.toString().split(",", 2);
			String[] arrCash = totalCash.toString().split(",", 2);

			System.out.println("monto:" + arrMont[0].replace("[",""));
			System.out.println("monto:" + arrCash[0].replace("[",""));

			float totalCardMonthly = Float.parseFloat(arrMont[0].replace("[",""));
			float totalCardCash = Float.parseFloat(arrCash[0].replace("[",""));
			
			System.out.printf("%.2f", totalCardMonthly);
			System.out.printf("%.2f", totalCardCash);
			
			String idCardMonthly = (arrMont[1].replace("]",""));
			String idCardCash = (arrCash[1].replace("]",""));
			
			System.out.println(idCardMonthly);
			System.out.println(idCardCash);

			if (Float.compare(totalCardMonthly, totalCardCash) == 0) {

				System.out.println("f1=f2");
				id_bank_most = idCardCash;

			} else if (Float.compare(totalCardMonthly, totalCardCash) < 0) {

				System.out.println("totalCardMonthly<totalCardCash");
				id_bank_most = idCardCash;
			} else {

				System.out.println("ftotalCardMonthly>totalCardCash");
				id_bank_most = idCardMonthly;
			}

			Bank bank1 = this.repository.findById(id_bank_most)
					.orElseThrow(() -> new TarjetasException("Can't find bank by id: 1"));

			return bank1;
		} catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}

	@Override
	public List<ArrayList> getBankMostImportCashByCard(Pageable pageable) {
        return repositoryPay.findBankMostImportCashByCard(pageable);
	}

	@Override
	public List<ArrayList> getBankMostImportMonthlyByCard(Pageable pageable) {
        return repositoryPay.findBankMostImportCashByCard(pageable);

	}

	public void save(Bank bank) {
		this.repository.save(bank);
	}

	public Optional<Bank> findById(String id) {
        return this.repository.findById(id);
	}
}
