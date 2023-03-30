package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPaymentRepository;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PaymentRepositoryImpl implements IPaymentRepository {
	
    private final MongoTemplate mongoTemplate;

    public PaymentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
	@Autowired
	private PaymentRepository repository;

    private Pageable paging = PageRequest.of(0, 1);

	@Override
	public String findTotalByMonth(String month) throws TarjetasException{
		try {

			List<ArrayList> totalMonthly = getTotalCashByMonth(month);
			List<ArrayList> totalCash = getTotalQuotasByMonth(month);


			float totalCardMonthly = Float.parseFloat(totalMonthly.toArray()[0].toString().replace("[","").replace("]", ""));
			float totalCardCash = Float.parseFloat(totalCash.toArray()[0].toString().replace("[","").replace("]", ""));
			
			System.out.println(totalMonthly.toArray()[0].toString().replace("[","").replace("]", ""));

			
			Float total = Float.sum(totalCardMonthly, totalCardCash);

			Map<String, Float> elements = new HashMap<String, Float>();
			elements.put("TOTAL        :", total);
			elements.put("Total Cash   : ", totalCardCash);
			elements.put("Total Monthly:", totalCardMonthly);

			ObjectMapper objectMapper = new ObjectMapper();

			String totales = objectMapper.writeValueAsString(elements);

			return totales;
		}
		catch (Exception e) {
			throw new TarjetasException(e.getMessage());
		}
	}
	
	@Override
	public List<ArrayList> getTotalCashByMonth(String month) {
        return repository.totalCashByMonth(month);
	}

	@Override
	public List<ArrayList> getTotalCashByMonth2(String month) {
        return repository.totalCashByMonth2(month);
	}
	
	@Override
	public List<ArrayList> getTotalQuotasByMonth(String month) {
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
	public Optional<Payment> findById(String id) {
        return this.repository.findById(id);
	}
//
//	@Override
//	public List<ArrayList> getStoreWithMoreSalesCash(String month) {
//        return repository.findStoreWithMoreSalesCash(month, paging);
//	}

    @Override
    public List<Payment> getStoreWithMoreSalesCash(String month) {
        Query query = new Query().addCriteria(Criteria.where("month").is(month));
        query.fields()
        .include("cashpayment.cuitStore")
        .include("cashpayment.amount")
        .include("cashpayment.store");
        return mongoTemplate.find(query, Payment.class);
    }
    
	public List<String> totalCashByMonth(String month) {
		AggregationOperation match = Aggregation.match(Criteria.where("month").is(month));
		AggregationOperation group = Aggregation.group("cashpayment.store").sum("cashpayment.amount").as("total");
		AggregationOperation project = Aggregation.project("total");
		
		Aggregation aggregation = Aggregation.newAggregation(match, group, project);

		List<String> saleInfo = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Payment.class), String.class).getMappedResults();
		
		return saleInfo;
	}
	
}
