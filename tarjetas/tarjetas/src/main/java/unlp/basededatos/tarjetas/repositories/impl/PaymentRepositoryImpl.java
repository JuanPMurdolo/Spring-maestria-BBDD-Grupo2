package unlp.basededatos.tarjetas.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPaymentRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PaymentRepositoryImpl implements IPaymentRepository {
	
	@Autowired
	private PaymentRepository repository;

	@Override
	@Transactional
	public String findTotalByMonth(String month) throws TarjetasException{
		try {
			float totalMonthly = getTotalCashByMonth(month);
			float totalCash = getTotalQuotasByMonth(month);

			Float total = Float.sum(totalMonthly, totalCash);

			Map<String, Float> elements = new HashMap<String, Float>();
			elements.put("TOTAL        :", total);
			elements.put("Total Cash   : ", totalCash);
			elements.put("Total Monthly:", totalMonthly);

			ObjectMapper objectMapper = new ObjectMapper();

			String totales = objectMapper.writeValueAsString(elements);

			return totales;
		}
		catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}
	
	@Override
	public float getTotalCashByMonth(String month) {
        return repository.totalCashByMonth(month);
	}

	@Override
	public float getTotalQuotasByMonth(String month) {
        return repository.totalQuotasByMonth(month);

	}

	@Override
	public List<Payment> findPaymentsByCode(String code) {
	    return this.repository.findPaymentsByCode(code);
	}

	@Override
	public void save(Payment payment) {
		this.repository.save(payment);
	}

	@Override
	public Optional<Payment> findById(Long id) {
        return this.repository.findById(id);
	}

    

}
