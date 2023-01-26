package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseService {

	    public Purchase createPurchase(Purchase purchase) throws TarjetasException;

	    public Purchase getPurchase(Long id) throws TarjetasException;

	    public Purchase updatePurchase(Purchase purchase,Long id) throws TarjetasException;
	    

}
