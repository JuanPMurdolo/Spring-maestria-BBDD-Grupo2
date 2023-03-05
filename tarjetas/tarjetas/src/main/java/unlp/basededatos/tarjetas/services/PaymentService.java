package unlp.basededatos.tarjetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface PaymentService {

    Optional<Payment> getPayment(String id) throws TarjetasException;

    List<Payment> getPaymentsByCode(String code) throws TarjetasException;

	void createPayment(Payment payment) throws TarjetasException;
	
	float getTotalCashByMonth(String month) throws TarjetasException;

	float getTotalQuotasByMonth(String month) throws TarjetasException;

	String getTotalByMonth(String month) throws TarjetasException;
	
}
