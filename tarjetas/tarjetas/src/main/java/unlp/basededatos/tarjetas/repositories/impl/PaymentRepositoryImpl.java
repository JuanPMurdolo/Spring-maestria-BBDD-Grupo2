package unlp.basededatos.tarjetas.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPaymentRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PaymentRepositoryImpl implements IPaymentRepository {
	
    private PaymentRepository repository;
    //private IPaymentRepository paymentRepository2;
	
//    public PaymentRepositoryImpl(PaymentRepository repository) {
//    	this.repository = repository;
//    }
    
    @Override
    @Transactional
    public String findTotalByMonth(String month) throws TarjetasException{
    	try {

    		float totalMonthly =  repository.totalQuotasByMonth(month);
    		float totalCash =  repository.totalCashByMonth(month);

    		Float total = Float.sum(totalMonthly,totalCash);  

    		Map<String, Float> elements = new HashMap<String, Float>();
    		elements.put("TOTAL        :", total);
    		elements.put("Total Cash   : ", totalCash);
    		elements.put("Total Monthly:", totalMonthly);

    		ObjectMapper objectMapper = new ObjectMapper();

    		String json = objectMapper.writeValueAsString(elements);
    		System.out.println(json);

    		return json;
    	}
    	catch (Exception e) {
    		throw new TarjetasException(e.getMessage());
    	}
    }

	@Override
    @Transactional
	public float getTotalCashByMonth(String month) throws TarjetasException {
        return repository.totalCashByMonth(month);
	}

	@Override
    @Transactional
	public float getTotalQuotasByMonth(String month) throws TarjetasException {
        return repository.totalCashByMonth(month);

	}

    

}
