package unlp.basededatos.tarjetas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.repositories.CardRepository;
import unlp.basededatos.tarjetas.services.CardService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository repository;

    @Override
    public Optional<Card> getCard(Long id) throws TarjetasException {
        return this.repository.findById(id);
    }

	@Override
	public void createCard(Card card) throws TarjetasException {
        this.repository.save(card);
	}

	@Override
	public List<Card> findCardExpirationDate(Date date, Date date1) throws TarjetasException {
		return this.repository.findCardExpirationDate(date,date1);

	}


}
