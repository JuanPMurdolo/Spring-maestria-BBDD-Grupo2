package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IBankRepository {

	Map<String, Object> getBankMostImportCashByCard();

	Map<String, Object> getBankMostImportMonthlyByCard();

	void save(Bank bank);

	Optional<Bank> findById(Long id);

	Bank getBankMostImportByCard() throws TarjetasException;

}
