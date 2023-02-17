package unlp.basededatos.tarjetas.repositories.interfaces;

import org.springframework.web.bind.annotation.PathVariable;

import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IPaymentRepository {

	String findTotalByMonth(String month) throws TarjetasException;
	
    float getTotalCashByMonth(String month) throws TarjetasException; 
    
    float getTotalQuotasByMonth(String month) throws TarjetasException; 


}
