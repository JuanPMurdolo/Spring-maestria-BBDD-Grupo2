package unlp.basededatos.tarjetas.services;


import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.utils.CardException;

public interface CardService {

    public Card createCard(Card card) throws CardException;
    public String create(String name, String mesage) throws CardException;

    public Card getCard(Long id) throws CardException;

    public Card updateCard(Card card,Long id) throws CardException;

}
