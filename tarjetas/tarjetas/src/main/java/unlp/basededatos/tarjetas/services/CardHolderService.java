package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardHolderService {

    Optional<CardHolder> getCardHolder(String id) throws TarjetasException;

    List<CardHolder> get10CardHolersWithMorePurchases(Pageable pageable) throws TarjetasException;

	void createCardHolder(CardHolder cardHolder)  throws TarjetasException;;


}
