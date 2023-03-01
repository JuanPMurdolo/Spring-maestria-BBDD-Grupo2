package unlp.basededatos.tarjetas.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.repositories.CardHolderRepository;
import unlp.basededatos.tarjetas.services.CardHolderService;
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
    public List<CardHolder> get10CardHolersWithMorePurchases(Pageable pageable) throws TarjetasException {
       return this.repository.get10CardHolersWithMorePurchases(pageable);
    }

	@Override
	public void createCardHolder(CardHolder cardHolder) {
         this.repository.save(cardHolder);
	}
}
