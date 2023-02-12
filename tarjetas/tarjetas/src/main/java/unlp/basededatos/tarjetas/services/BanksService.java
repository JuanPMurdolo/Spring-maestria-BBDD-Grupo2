package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.model.Promotion;

public interface BanksService {

	    public void createBank(Bank bank) throws TarjetasException;

	    public Optional<Bank> getBank(Long id) throws TarjetasException;

}
