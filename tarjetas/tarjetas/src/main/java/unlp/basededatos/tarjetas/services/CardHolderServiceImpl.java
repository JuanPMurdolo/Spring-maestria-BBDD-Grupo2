package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.repositories.CardHolderRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Service
public class CardHolderServiceImpl implements CardHolderService {

    @Autowired
    private CardHolderRepository repository;

    @Override
    @Transactional
    public CardHolder createCardHolder(CardHolder cardHolder) throws TarjetasException {
        Long id = this.repository.saveCardHolder(cardHolder);
        return this.repository.findCardHolderById(id);
    }

    @Override
    @Transactional
    public String create(String name, String mesage) throws TarjetasException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CardHolder getCardHolder(Long id) throws TarjetasException {
        return this.repository.findCardHolderById(id);
    }

    @Override
    public CardHolder updateCardHolder(CardHolder cardHolder, Long id) throws TarjetasException {
        CardHolder cardHolder1 = this.repository.findCardHolderById(id);
        cardHolder1.setAddress(cardHolder.getAddress());
        cardHolder1.setCuil(cardHolder.getCuil());
        cardHolder1.setTelephone(cardHolder.getTelephone());
        cardHolder1.setDni(cardHolder.getDni());
        cardHolder1.setCompleteName(cardHolder.getCompleteName());
        cardHolder1.setEntry(cardHolder.getEntry());
        return cardHolder1;
    }
}
