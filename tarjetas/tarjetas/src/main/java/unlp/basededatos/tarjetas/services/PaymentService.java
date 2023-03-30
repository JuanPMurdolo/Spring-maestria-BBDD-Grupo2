package unlp.basededatos.tarjetas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface PaymentService {

    Optional<Payment> getPayment(String id) throws TarjetasException;

    List<Payment> getPaymentsByCode(String code) throws TarjetasException;

	void createPayment(Payment payment) throws TarjetasException;
	
	List<ArrayList> getTotalCashByMonth(String month) throws TarjetasException;

	List<ArrayList> getTotalQuotasByMonth(String month) throws TarjetasException;

	String getTotalByMonth(String month) throws TarjetasException;

	List<ArrayList> getTotalCashByMonth2(String month);

	List<Payment> getStoreWithMoreSalesCash(String month);

}
