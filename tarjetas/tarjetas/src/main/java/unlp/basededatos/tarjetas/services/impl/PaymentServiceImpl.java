package unlp.basededatos.tarjetas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.services.PaymentService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository repository;

	@Override
	public void createPayment(Payment payment) throws TarjetasException {
		this.repository.save(payment);
	}

	@Override
	public Optional<Payment> getPayment(Long id) throws TarjetasException {
        return this.repository.findById(id);
	}

	@Override
	public List<Payment> getPaymentsByCode(String code) throws TarjetasException {
	    return this.repository.findPaymentsByCode(code);
	}


}
