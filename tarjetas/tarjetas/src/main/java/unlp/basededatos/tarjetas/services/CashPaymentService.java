package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.CashPayment;

import java.util.Optional;

public interface CashPaymentService {

	    CashPayment createCashPayment(CashPayment cashPayment) throws TarjetasException;

	    Optional<CashPayment> getCashPayment(String id) throws TarjetasException;

	    CashPayment updateCashPayment(CashPayment cashPayment,String id) throws TarjetasException;

    

}
