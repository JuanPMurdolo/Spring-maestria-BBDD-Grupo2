package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Promotion;
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


	@Transactional
	public Promotion addNewPromotion(Promotion promotion, Long id) throws TarjetasException {

		// Obtengo el Banco
		Bank bank = repository.findBankById(id);

		// Si el Banco no existe, retorno false
		if (bank ==  null) {
			System.out.println("El banco no existe");
			return null;
		}

		bank.addPromotion(promotion);
		bank = this.repository.findBankById(repository.saveBank(bank));

		return bank.getPromotions().get( bank.getPromotions().size() -1 );
	}

	/*
	 * public long bankCount() throws Exception { return repository.count(); }
	 * 
	 * public List<Bank> getAllBanks() throws Exception { // Retorno todos los
	 * Bankes return repository.findAll(); }
	 * 
	 * public List<Promotion> getAddresses(long idBank) throws Exception { //
	 * Obtengo el Banke Bank bank = repository.getBankById( idBank );
	 * 
	 * // Si el banke no existe, retorno false if (bank == null) {
	 * System.out.println("El banke no existe"); return null; }
	 * 
	 * return bank.getAddresses(); }
	 */

}
