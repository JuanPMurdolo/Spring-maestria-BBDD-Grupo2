package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IPurchaseRepository {
	
	ArrayList getStoreWithMoreSales(String month) throws TarjetasException;

	List<ArrayList> getStoreWithMoreSalesCash(String month);

	List<ArrayList> getStoreWithMoreSalesMonthly(String month);

	Optional<Purchase> findById(String id);
}
