package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseService {

	    Purchase createPurchase(Purchase purchase) throws TarjetasException;

	    Purchase getPurchase(Long id) throws TarjetasException;

	    Purchase updatePurchase(Purchase purchase,Long id) throws TarjetasException;
	    

}
