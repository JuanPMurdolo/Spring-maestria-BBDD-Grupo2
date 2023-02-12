package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import unlp.basededatos.tarjetas.services.BanksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class BanksServiceImpl implements BanksService{

    @Autowired
    private BankRepository repository;


    @Override
    public Optional<Bank> getBank(Long id) throws TarjetasException {
        return this.repository.findById(id);
    }

	@Override
	public void createBank(Bank bank) {
         this.repository.save(bank);
	}


}
