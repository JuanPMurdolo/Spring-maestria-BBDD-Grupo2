package unlp.basededatos.tarjetas.repositories.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IBankRepository;
import unlp.basededatos.tarjetas.utils.BankDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class BankRepositoryImpl implements IBankRepository {
	
	@Autowired
	private BankRepository repository;

	@Override
	public Bank getBankMostImportByCard() throws TarjetasException {
		try {

			String id_bank_most = null;
	        Pageable paging = PageRequest.of(0, 1);

			List<BankDTO> totalMonthly = getBankMostImportCashByCard(paging);
			List<BankDTO> totalCash = getBankMostImportMonthlyByCard(paging);

			for(int i=0; i < totalMonthly.size(); i++){
			    System.out.println("total: " +  totalMonthly.get(i).getTotal().toString() );
			    System.out.println( totalMonthly.get(i).getBank().toString() );
			    System.out.println( totalMonthly.get(i).getCard().toString() );
			}
			
			for(int i=0; i < totalCash.size(); i++){
			    System.out.println( totalCash.get(i).getTotal() );
			    System.out.println( totalCash.get(i).getBank() );
			    System.out.println( totalCash.get(i).getCard() );
			}
			
			System.out.println(totalMonthly.toString()); 
			System.out.println(totalCash); 
					

			float totalCardMonthly = Float.parseFloat(totalMonthly.get(0).getTotal().toString());
			float totalCardCash = Float.parseFloat(totalCash.get(0).getTotal().toString());
			System.out.printf("%.2f", totalCardMonthly);
			System.out.printf("%.2f", totalCardCash);

			if (Float.compare(totalCardMonthly, totalCardCash) == 0) {

				System.out.println("f1=f2");
			} else if (Float.compare(totalCardMonthly, totalCardCash) < 0) {

				System.out.println("totalCardMonthly<totalCardCash");
				id_bank_most = totalCash.get(0).getBank().toString();
			} else {

				System.out.println("ftotalCardMonthly>totalCardCash");
				id_bank_most = totalMonthly.get(0).getBank().toString();

			}

			Bank bank1 = this.repository.findById(id_bank_most)
					.orElseThrow(() -> new TarjetasException("Can't find bank by id: 1"));

			return bank1;
		} catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}

	@Override
	public List<BankDTO> getBankMostImportCashByCard(Pageable pageable) {
        return repository.findBankMostImportCashByCard(pageable);
	}

	@Override
	public List<BankDTO> getBankMostImportMonthlyByCard(Pageable pageable) {
        return repository.findBankMostImportMonthlyByCard(pageable);

	}

	public void save(Bank bank) {
		this.repository.save(bank);
	}

	public Optional<Bank> findById(String id) {
        return this.repository.findById(id);
	}
}
