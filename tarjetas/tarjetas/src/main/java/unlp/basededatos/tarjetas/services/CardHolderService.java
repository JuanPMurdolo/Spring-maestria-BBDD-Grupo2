package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardHolderService {

    public CardHolder createCardHolder(CardHolder card) throws TarjetasException;

    public String create(String name, String mesage) throws TarjetasException;

    public CardHolder getCardHolder(Long id) throws TarjetasException;

    public CardHolder updateCardHolder(CardHolder cardHolder,Long id) throws TarjetasException;

}
