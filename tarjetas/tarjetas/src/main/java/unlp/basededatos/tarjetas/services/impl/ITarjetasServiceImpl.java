package unlp.basededatos.tarjetas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.gson.*;

import org.springframework.transaction.annotation.Transactional;

import unlp.basededatos.tarjetas.model.*;
import unlp.basededatos.tarjetas.repositories.*;
import unlp.basededatos.tarjetas.repositories.interfaces.IBankRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPaymentRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPurchaseRepository;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ITarjetasServiceImpl implements ITarjetasService{
    
	@Autowired
    private IPaymentRepository paymentRepository;
	
	@Autowired
    private IBankRepository bankRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private IPurchaseRepository purchaseRepository;

	@Autowired
	private MonthlyPaymentRepository monthlyPaymentRepository;

	@Autowired
	private CashPaymentRepository cashPaymentRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private CardHolderRepository cardHolderRepository;
    
    @Override
	@Transactional
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException {
        //obtener todas los payments que se correspondan con el code
        List<Payment> lista = this.paymentRepository.findPaymentsByCode(code);
		List<Payment> lista2 = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            //Leer el payment en dicha posicion
            Payment payment1 = lista.get(i);
            //se modifica la fecha de expiracion
            payment1.setFirstExpiration(first);
            payment1.setSecondExpiration(second);
            //Se actualiza el payment
            this.paymentRepository.save(payment1);
			lista2.add(payment1);
        }
        return lista2;
    }
    
	@Transactional
	public Promotion addNewPromotion(Promotion promotion, String id) throws TarjetasException {

		bankRepository.findById(id);
		
		Bank bank = bankRepository.findById(id)
                .orElseThrow(()-> new TarjetasException("Can't find bank by id: "+id));
        
		bank.addPromotion(promotion);
		bankRepository.save(bank);

		return bank.getPromotions().get( bank.getPromotions().size() -1 );
	}

	@Override
	@Transactional
	public List<Card> getCardSoonExpiration() throws TarjetasException {

		Date date = new Date();
		LocalDate localDate = LocalDate.now().plusDays(30);
		Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		//return this.cardRepository.findCardExpirationDate(date,date1);
		return this.cardRepository.findByExpirationDateBetween(date,date1);
	}

	@Override
	public Optional<Purchase> getPurchaseInfo(String id) throws TarjetasException {
		
		Purchase purchase = null;
		boolean resultMonthly = monthlyPaymentRepository.existsById(id);
		//boolean resultCash = cashPaymentRepository.existsById(id);

		System.out.println("Check Exists: " + resultMonthly);

		if (resultMonthly) {
			MonthlyPayments monthlyPayments = this.monthlyPaymentRepository.findById(id)
					.orElseThrow(() -> new TarjetasException("Can't find MonthlyPayment by id: " + id));
			purchase = monthlyPayments;

		} else {
			CashPayment cashPayments = this.cashPaymentRepository.findById(id)
					.orElseThrow(() -> new TarjetasException("Can't find CashPayment by id: " + id));
			purchase = cashPayments;
		}
		
		return Optional.ofNullable(purchase);
	}

	@Override
	@Transactional
	public void deletePromotion(String code) throws TarjetasException {
        this.promotionRepository.deletePromotion(code);

	}

	@Override
	@Transactional
	public float totalQuota(String id) throws TarjetasException {
		Float totalPayment = (float) 0;
		MonthlyPayments monthlyPayments = this.monthlyPaymentRepository.findById(id)
                .orElseThrow(()-> new TarjetasException("Can't find MonthlyPayment by id: "+id));
		List<Quota> quotas = monthlyPayments.getQuotas();
		for (int i = 0; i < quotas.size(); i++){
			totalPayment = totalPayment + quotas.get(i).getPrice();
		}
		return totalPayment;
	}

	@Override
	@Transactional
	public List<Promotion> promotionListBetweenDates(String cuit, Date date, Date date1) throws TarjetasException {
		return this.promotionRepository.findPromotionByCuitByDate(cuit,date,date1);
	}

	@Override
	@Transactional
	public List<CardHolder> get10CardHolersWithMorePurchases() throws TarjetasException {
		return this.cardHolderRepository.get10CardHolersWithMorePurchases(PageRequest.of(0, 10));
	}

	@Override
	public Optional<Promotion> getPromotionMostUsed() throws TarjetasException{
		Pageable paging = PageRequest.of(0, 1);

		
		System.out.println(cashPaymentRepository.getOccurences(paging));
		System.out.println(cashPaymentRepository.getOccurencesMonthly(paging));

		JsonObject jsonObj = new JsonObject();
	      // array to JsonArray
		JsonArray jsonArray1 = new Gson().toJsonTree(cashPaymentRepository.getOccurences(paging)).getAsJsonArray();
		// ArrayList to JsonArray
		JsonArray jsonArray2 = new Gson().toJsonTree(cashPaymentRepository.getOccurences(paging)).getAsJsonArray();
		jsonObj.add("jsonArray1", jsonArray1);
		//jsonObj.add("jsonArray2", jsonArray2);
		System.out.println(jsonObj.toString());

		// Use this builder to construct a Gson instance when you need to set configuration options other than the default.
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it. 
        // Gson instances are Thread-safe so you can reuse them freely across multiple threads.
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(cashPaymentRepository.getOccurences(paging));
        System.out.println("\nConverted JSONObject ==> " + JSONObject);
        
        
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(cashPaymentRepository.getOccurences(paging));
        
        System.out.println("\nPretty JSONObject ==> " + prettyJson);
        
      //getting values form the JSONObject  
        
        
		int cash = Integer.parseInt(this.cashPaymentRepository.getOccurences(paging).get(0));
		int monthly = Integer.parseInt(this.cashPaymentRepository.getOccurencesMonthly(paging).get(0));
		if (cash > monthly) {
		return this.promotionRepository.findById(this.cashPaymentRepository.getMostUsed(paging).get(0).toString());
		} else {
			return this.promotionRepository.findById(this.cashPaymentRepository.getMostUsedMonthly(paging).get(0).toString());
		}
	}
		
	@Override
	@Transactional
	public String getTotalByMonth(String month) throws TarjetasException {
		return this.paymentRepository.findTotalByMonth(month);
	}

	@Override
	@Transactional
	public ArrayList getInfoFromBusiness(String month) throws TarjetasException{
		return this.purchaseRepository.getStoreWithMoreSales(month);
	}
	
	@Override
	public Bank getBankMostImportByCard() throws TarjetasException {
		return this.bankRepository.getBankMostImportByCard();

	}


}
