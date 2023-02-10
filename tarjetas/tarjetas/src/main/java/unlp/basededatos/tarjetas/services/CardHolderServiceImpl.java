package unlp.basededatos.tarjetas.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public Optional<CardHolder> getCardHolder(Long id) throws TarjetasException {
        return this.repository.findById(id);
    }

    @Override
    public List<CardHolder> get10CardHolersWithMorePurchases() throws TarjetasException {
       return this.repository.get10CardHolersWithMorePurchases();
    }

	@Override
	public void createCardHolder(CardHolder cardHolder2) {
         this.repository.save(cardHolder2);
	}
}
