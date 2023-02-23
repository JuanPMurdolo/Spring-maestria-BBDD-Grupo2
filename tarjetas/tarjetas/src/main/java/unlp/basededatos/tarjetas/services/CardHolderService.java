package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardHolderService {

    Optional<CardHolder> getCardHolder(Long id) throws TarjetasException;

    List<CardHolder> get10CardHolersWithMorePurchases() throws TarjetasException;

	void createCardHolder(CardHolder cardHolder)  throws TarjetasException;;


}
