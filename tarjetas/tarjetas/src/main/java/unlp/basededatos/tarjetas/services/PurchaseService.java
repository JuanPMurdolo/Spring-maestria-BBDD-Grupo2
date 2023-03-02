package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;

import java.util.Optional;

public interface PurchaseService {

	    Purchase createPurchase(Purchase purchase) throws TarjetasException;

	    Optional<Purchase> getPurchase(Long id) throws TarjetasException;

	    Purchase updatePurchase(Purchase purchase,Long id) throws TarjetasException;
	    

}
