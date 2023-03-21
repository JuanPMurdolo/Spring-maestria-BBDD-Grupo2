package unlp.basededatos.tarjetas.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface IPaymentRepository {

	String findTotalByMonth(String month) throws TarjetasException;
	
    List<ArrayList> getTotalCashByMonth(String month) throws TarjetasException; 
    
    List<ArrayList> getTotalQuotasByMonth(String month) throws TarjetasException;

	List<Payment> findPaymentsByCode(String code);

	void save(Payment payment);

	Optional<Payment> findById(String id);

	List<ArrayList> getTotalCashByMonth2(String month);

	List<ArrayList> getStoreWithMoreSalesCash(String month);


}
