package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.model.Promotion;

public interface BanksService {

	    void createBank(Bank bank) throws TarjetasException;

	    Optional<Bank> getBank(String id) throws TarjetasException;

}
