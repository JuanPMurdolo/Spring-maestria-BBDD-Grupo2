package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.repositories.CardRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Date;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository repository;

    @Override
    @Transactional
    public Card createCard(Card card) throws TarjetasException {
        Long id = this.repository.saveCard(card);
        return this.repository.findCardById(id);
    }

    @Override
    @Transactional
    public String create(String name, String mesage) throws TarjetasException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Card getCard(Long id) throws TarjetasException {
        return this.repository.findCardById(id);
    }

    @Override
    public Card updateCard(Card card, Long id) throws TarjetasException {
        Card card1 = this.repository.findCardById(id);
        card1.setCardholderNameInCard(card.getCardholderNameInCard());
        card1.setCcv(card.getCcv());
        card1.setNumber(card.getNumber());
        card1.setSince(card.getSince());
        card1.setExpirationDate(card.getExpirationDate());
        return card1;
    }

}
