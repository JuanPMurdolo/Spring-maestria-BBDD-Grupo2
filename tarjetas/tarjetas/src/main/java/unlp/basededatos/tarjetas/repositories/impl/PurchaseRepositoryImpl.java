package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import unlp.basededatos.tarjetas.model.Payment;
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
	
	@Autowired
	private PaymentRepositoryImpl repositoryPayImpl;
	
    private Pageable paging = PageRequest.of(0, 1);


	@Override
	@Transactional
	public ArrayList getStoreWithMoreSales(String month) throws TarjetasException {
		try {
			
			List<ArrayList> totalMonthly = getStoreWithMoreSalesCash(month);
			List<ArrayList> totalCash = getStoreWithMoreSalesMonthly(month);
		
			System.out.println("payment: " + totalMonthly.get(0).get(0));
			System.out.println("payment: " + totalCash.get(0).get(0));

			Payment auxMonthly = (Payment) totalMonthly.get(0).get(0);
			Payment auxCash = (Payment) totalCash.get(0).get(0);

			System.out.println("payment: " + auxMonthly.getCashpayments().get(0).getCuitStore());
			System.out.println("payment: " + auxMonthly.getCashpayments().get(0).getAmount());
			System.out.println("payment: " + auxMonthly.getCashpayments().get(0).getStore());

		
			float totalCardMonthly = auxMonthly.getCashpayments().get(0).getAmount();
			float totalCardCash = auxCash.getCashpayments().get(0).getAmount();

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
