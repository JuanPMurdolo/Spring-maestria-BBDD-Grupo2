package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.utils.BankDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IBankRepository {

	List<BankDTO> getBankMostImportCashByCard(Pageable pageable);

	List<BankDTO> getBankMostImportMonthlyByCard(Pageable pageable);

	void save(Bank bank);

	Optional<Bank> findById(String id);

	Bank getBankMostImportByCard() throws TarjetasException;

}
