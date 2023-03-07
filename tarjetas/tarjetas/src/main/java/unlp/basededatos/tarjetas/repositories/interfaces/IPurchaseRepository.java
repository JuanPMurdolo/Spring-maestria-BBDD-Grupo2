package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IPurchaseRepository {
	
	PurchaseDTO getStoreWithMoreSales(String month) throws TarjetasException;

	List<PurchaseDTO> getStoreWithMoreSalesCash(String month);

	List<PurchaseDTO> getStoreWithMoreSalesMonthly(String month);

	Optional<Purchase> findById(String id);
}
