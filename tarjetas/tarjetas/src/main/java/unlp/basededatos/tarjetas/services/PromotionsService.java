package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Promotion;

public interface PromotionsService {

	    Promotion createPromotion(Promotion promotion) throws TarjetasException;

	    Promotion getPromotion(Long id) throws TarjetasException;

	    Promotion updatePromotion(Promotion promotion,Long id) throws TarjetasException;
	    
	    void deletePromotion(String code) throws TarjetasException;

}
