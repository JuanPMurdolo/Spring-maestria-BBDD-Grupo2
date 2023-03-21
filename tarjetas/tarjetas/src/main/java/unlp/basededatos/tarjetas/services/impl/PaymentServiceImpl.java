package unlp.basededatos.tarjetas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.repositories.interfaces.IPaymentRepository;
import unlp.basededatos.tarjetas.services.PaymentService;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private IPaymentRepository repository;

	@Override
	public void createPayment(Payment payment) throws TarjetasException {
		this.repository.save(payment);
	}

	@Override
	public Optional<Payment> getPayment(String id) throws TarjetasException {
		return this.repository.findById(id);
	}

	@Override
	public List<Payment> getPaymentsByCode(String code) throws TarjetasException {
		return this.repository.findPaymentsByCode(code);
	}

	@Override
	public float getTotalCashByMonth(String month) throws TarjetasException {
		return this.repository.getTotalCashByMonth(month);
	}

	@Override
	public float getTotalQuotasByMonth(String month) throws TarjetasException {
		return this.repository.getTotalQuotasByMonth(month);
	}

	@Override
	public String getTotalByMonth(String month) throws TarjetasException {
		return this.repository.findTotalByMonth(month);
	}

	@Override
	public List<PaymentDTO> getTotalCashByMonth2(String month) {
		return this.repository.getTotalCashByMonth2(month);
	}

	public List<ArrayList> getStoreWithMoreSalesCash(String month) {
		return this.repository.getStoreWithMoreSalesCash(month);

	}

}
