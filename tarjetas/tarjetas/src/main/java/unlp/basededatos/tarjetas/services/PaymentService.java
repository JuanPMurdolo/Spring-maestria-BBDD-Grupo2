package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface PaymentService {

    public Optional<Payment> getPayment(Long id) throws TarjetasException;

    public List<Payment> getPaymentsByCode(String code) throws TarjetasException;

	public void createPayment(Payment payment) throws TarjetasException;
	
}
