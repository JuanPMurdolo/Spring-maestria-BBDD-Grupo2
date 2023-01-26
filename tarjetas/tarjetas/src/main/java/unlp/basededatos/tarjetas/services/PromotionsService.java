package unlp.basededatos.tarjetas.services;

import java.util.List;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Promotion;

public interface PromotionsService {

	    public Promotion createPromotion(Promotion promotion) throws TarjetasException;

	    public Promotion getPromotion(Long id) throws TarjetasException;

	    public Promotion updatePromotion(Promotion promotion,Long id) throws TarjetasException;
	    

}
