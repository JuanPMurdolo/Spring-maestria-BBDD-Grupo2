package unlp.basededatos.tarjetas.services;

import java.util.List;

import unlp.basededatos.tarjetas.utils.BankException;
import unlp.basededatos.tarjetas.model.Bank;

public interface BanksService {

	    public Bank createBank(Bank bank) throws BankException;
	    public String create(String name, String mesage) throws BankException; 

	    public Bank getBank(Long id) throws BankException;

	    public Bank updateBank(Bank bank,Long id) throws BankException;


}
