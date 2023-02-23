package unlp.basededatos.tarjetas.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface CardService {

    void createCard(Card card) throws TarjetasException;

    Optional<Card> getCard(Long id) throws TarjetasException;
    
    List<Card> findCardExpirationDate(Date date, Date date1) throws TarjetasException;


}
