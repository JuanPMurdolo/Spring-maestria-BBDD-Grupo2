package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

@Service
public class BanksServiceImpl implements BanksService{

    @Autowired
    private BankRepository repository;

    @Override
    @Transactional
    public Bank createBank(Bank bank) throws TarjetasException {
        Long id = this.repository.saveBank(bank);
        return this.repository.findBankById(id);
    }

    @Override
    @Transactional
    public Bank getBank(Long id) throws TarjetasException {
        return this.repository.findBankById(id);
    }

    @Override
    @Transactional
    public Bank updateBank(Bank bank, Long id) throws TarjetasException  {
        Bank bank1 =  this.repository.findBankById(id);
        bank1.setAddress(bank.getAddress());
        bank1.setName(bank.getName());
        this.repository.updateBank(bank1);
        return bank1;
    }

	@Override
	public String create(String name, String mesage) throws TarjetasException {
		// TODO Auto-generated method stub
		return null;
	}

}
