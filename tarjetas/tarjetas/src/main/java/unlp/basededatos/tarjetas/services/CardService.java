package unlp.basededatos.tarjetas.services;


import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardService {

    public Card createCard(Card card) throws TarjetasException;
    public String create(String name, String mesage) throws TarjetasException;

    public Card getCard(Long id) throws TarjetasException;

    public Card updateCard(Card card,Long id) throws TarjetasException;

}
