package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IPromotionRepository {

	Long deletePromotion(String code);
	
	Promotion getPromotionMostUsed();

	
}
