package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardHolderService {

    public CardHolder createCardHolder(CardHolder card) throws TarjetasException;

    public String create(String name, String mesage) throws TarjetasException;

    public CardHolder getCard(Long id) throws TarjetasException;

    public CardHolder updateCard(CardHolder cardHolder,Long id) throws TarjetasException;

}
