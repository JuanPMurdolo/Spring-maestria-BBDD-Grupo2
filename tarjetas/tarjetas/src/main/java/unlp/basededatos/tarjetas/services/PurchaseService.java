package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;

import java.util.Optional;

public interface PurchaseService {

	    Purchase createPurchase(Purchase purchase) throws TarjetasException;

	    Optional<Purchase> getPurchase(String id) throws TarjetasException;

	    Purchase updatePurchase(Purchase purchase,String id) throws TarjetasException;
	    

}
