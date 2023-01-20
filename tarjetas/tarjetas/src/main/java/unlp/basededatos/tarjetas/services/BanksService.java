package unlp.basededatos.tarjetas.services;

import java.util.List;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Promotion;

public interface BanksService {

	    public Bank createBank(Bank bank) throws TarjetasException;
	    public String create(String name, String mesage) throws TarjetasException; 

	    public Bank getBank(Long id) throws TarjetasException;

	    public Bank updateBank(Bank bank,Long id) throws TarjetasException;
	    
		public Promotion addNewPromotion(Promotion promotion, Long id) throws TarjetasException;


}
