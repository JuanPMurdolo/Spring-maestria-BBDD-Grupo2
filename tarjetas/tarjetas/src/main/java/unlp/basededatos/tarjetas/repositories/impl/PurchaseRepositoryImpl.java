package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.PurchaseRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPurchaseRepository;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PurchaseRepositoryImpl implements IPurchaseRepository{
	
	@Autowired
	private PurchaseRepository repository;

	@Autowired
	private PaymentRepository repositoryPay;
	
    private Pageable paging = PageRequest.of(0, 1);


	@Override
	@Transactional
	public ArrayList getStoreWithMoreSales(String month) throws TarjetasException {
		try {
			List<ArrayList> totalMonthly = getStoreWithMoreSalesCash(month);
			List<ArrayList> totalCash = getStoreWithMoreSalesMonthly(month);

			System.out.println(totalMonthly);
			System.out.println(totalMonthly.toArray());

			System.out.println(totalCash);

			String[] arrMont = totalMonthly.toString().split(",", 3);
			String[] arrCash = totalCash.toString().split(",", 3);

			System.out.println("monto:" + arrMont[1]);

			
			float totalCardMonthly = Float.parseFloat(arrMont[1]);
			float totalCardCash = Float.parseFloat(arrCash[1]);

			System.out.printf("%.2f", totalCardMonthly);
			System.out.printf("%.2f", totalCardCash);

			if (Float.compare(totalCardMonthly, totalCardCash) == 0) {
				System.out.println("f1=f2");
				return totalCash.get(0);
			} else if (Float.compare(totalCardMonthly, totalCardCash) < 0) {

				System.out.println("totalCardMonthly<totalCardCash");
				return totalCash.get(0);
			} else {

				System.out.println("ftotalCardMonthly>totalCardCash");
				return totalMonthly.get(0);
			}

		}
		catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}

	@Override
	public List<ArrayList> getStoreWithMoreSalesCash(String month) {
        return repositoryPay.findStoreWithMoreSalesCash(month, paging);
	}

	@Override
	public List<ArrayList> getStoreWithMoreSalesMonthly(String month) {
        return repositoryPay.findStoreWithMoreSalesCash(month, paging);
	}

	@Override
	public Optional<Purchase> findById(String id) {
        return this.repository.findById(id);
	}
}
