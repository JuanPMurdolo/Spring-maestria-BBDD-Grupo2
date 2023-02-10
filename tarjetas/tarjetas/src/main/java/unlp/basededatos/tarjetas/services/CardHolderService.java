package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardHolderService {

    public Optional<CardHolder> getCardHolder(Long id) throws TarjetasException;

    public List<CardHolder> get10CardHolersWithMorePurchases() throws TarjetasException;

	public void createCardHolder(CardHolder cardHolder2);;


}
