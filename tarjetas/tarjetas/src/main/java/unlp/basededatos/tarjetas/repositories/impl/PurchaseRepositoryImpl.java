package unlp.basededatos.tarjetas.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.repositories.PurchaseRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPurchaseRepository;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PurchaseRepositoryImpl implements IPurchaseRepository{

	@Autowired
	private PurchaseRepository repository;
	
    private Pageable paging = PageRequest.of(0, 1);

    
	@Override
	@Transactional
	public PurchaseDTO getStoreWithMoreSales(String month) throws TarjetasException {
		try {
			List<PurchaseDTO> totalMonthly = getStoreWithMoreSalesCash(month);
			List<PurchaseDTO> totalCash = getStoreWithMoreSalesMonthly(month);

			float totalCardMonthly = Float.parseFloat(totalMonthly.get(0).getAmount().toString());
			float totalCardCash = Float.parseFloat(totalCash.get(0).getAmount().toString());
			
			System.out.printf("%.2f", totalCardMonthly);
			System.out.printf("%.2f", totalCardCash);

			if (Float.compare(totalCardMonthly, totalCardCash) == 0) {
				System.out.println("f1=f2");
			} else if (Float.compare(totalCardMonthly, totalCardCash) < 0) {

				System.out.println("totalCardMonthly<totalCardCash");
				return totalCash.get(0);
			} else {

				System.out.println("ftotalCardMonthly>totalCardCash");
				return totalMonthly.get(0);
			}
			
			return null;
		}
		catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}
	
	@Override
	public List<PurchaseDTO> getStoreWithMoreSalesCash(String month) {
        return repository.findStoreWithMoreSalesCash(month, paging);
	}

	@Override
	public List<PurchaseDTO> getStoreWithMoreSalesMonthly(String month) {
        return repository.findStoreWithMoreSalesMonthly(month, paging);
	}

	@Override
	public Optional<Purchase> findById(Long id) {
        return this.repository.findById(id);
	}
}
