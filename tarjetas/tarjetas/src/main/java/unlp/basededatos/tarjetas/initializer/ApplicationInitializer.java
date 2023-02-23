package unlp.basededatos.tarjetas.initializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import unlp.basededatos.tarjetas.model.*;
import unlp.basededatos.tarjetas.services.*;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import javax.xml.crypto.Data;

@Component
public class ApplicationInitializer implements CommandLineRunner {
    
	@Autowired
	BanksService bankService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	CardHolderService cardHolderService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	PromotionsService promotionsService;

	@Autowired
	QuotaService quotaService;
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	ITarjetasService tarjetasService;

	////////
	// URL Swagger: http://localhost:8081/swagger-ui/index.html
	////////

	public void run(String... args) throws Exception {

		crearBancos();
		//crearPagos();
		//crearPunto1();
		//crearPurchases();
		crearBancos2();
		crearTitulares();
		//crearCards();
		//crearCashyPromos(); 

	}

	public void crearBancos() throws TarjetasException {

		Date fecha = new Date();

		CardHolder cardHolder1 = new CardHolder();
		cardHolder1.setCompleteName("Abramcito");
		cardHolder1.setDni("123456");
		cardHolder1.setTelephone("654321");
		cardHolder1.setCuil("11-4681561-8");
		//cardHolderService.createCardHolder(cardHolder1);
		
        CardHolder cardHolder2 = new CardHolder();
		cardHolder2.setCompleteName("Pablito");
		cardHolder2.setDni("345673546");
		cardHolder2.setTelephone("34561234");
		cardHolder2.setCuil("11-1213454-8");
		//cardHolderService.createCardHolder(cardHolder2);

        List<CardHolder> cardHoldersList = new ArrayList<CardHolder>();
        cardHoldersList.add(cardHolder1);
        cardHoldersList.add(cardHolder2);
        
		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
        bank1.setCardHolders(cardHoldersList);
        
		bankService.createBank(bank1);
		
        //creo tarjetas y le seteo el banco
		Card card1 = new Card();
        card1.setCardholderNameInCard("Abramcito");
        card1.setCcv("500");
        card1.setNumber("123456");
        card1.setOwner(cardHolder1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try{
		Date dateFormated1 = sdf.parse("2023-02-05");
		card1.setExpirationDate(dateFormated1);
		}
		catch (Exception e)
		{
			throw new TarjetasException(e.getMessage());
		}
		
        Card card2 = new Card();
        card2.setCardholderNameInCard("Pablito");
        card2.setCcv("456");
        card2.setNumber("681254");
        card2.setOwner(cardHolder2);

		//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try{
		Date dateFormated1 = sdf.parse("2023-02-02");
		card2.setExpirationDate(dateFormated1);
		}
		catch (Exception e)
		{
			throw new TarjetasException(e.getMessage());
		}

        card1.setBank(bank1);
        card2.setBank(bank1);
        
		cardService.createCard(card1);
		cardService.createCard(card2);

		CardHolder cardHolder3 = new CardHolder();
		cardHolder3.setCompleteName("Mabel");
		cardHolder3.setDni("123456");
		cardHolder3.setTelephone("654321");
		cardHolder3.setCuil("11-4681561-8");
		cardHolderService.createCardHolder(cardHolder3);
		
        CardHolder cardHolder4 = new CardHolder();
        cardHolder4.setCompleteName("Rosaura");
        cardHolder4.setDni("345673546");
        cardHolder4.setTelephone("34561234");
        cardHolder4.setCuil("11-1213454-8");
		cardHolderService.createCardHolder(cardHolder4);

        List<CardHolder> cardHoldersList2 = new ArrayList<CardHolder>();
        cardHoldersList.add(cardHolder3);
        cardHoldersList.add(cardHolder4);
        
		Bank bank2 = new Bank();
		bank2.setName("Banco Galicia");
		bank2.setAddress("San Martin 1234");
		bank2.setCuit("20-1245454-2");
		bank2.setTelephone("98765412");
        bank2.setCardHolders(cardHoldersList2);
        bankService.createBank(bank2);

		Bank bank3 = new Bank();
		bank3.setName("Banco Patagonia");
		bank3.setAddress("Maipu 1234");
		bank3.setCuit("20-33344454-2");
		bank3.setTelephone("1634134");
		bank3.setCardHolders(cardHoldersList2);
        bankService.createBank(bank3);

        //creo tarjetas y le seteo el banco
        Card card3 = new Card();
        card3.setCardholderNameInCard("Juanita");
        card3.setCcv("500");
        card3.setNumber("123456");
        card3.setOwner(cardHolder3);

        try{
        	Date dateFormated1 = sdf.parse("2023-02-05");
        	card3.setExpirationDate(dateFormated1);
        }
        catch (Exception e)
        {
        	throw new TarjetasException(e.getMessage());
        }

        Card card4 = new Card();
        card4.setCardholderNameInCard("Estelita");
        card4.setCcv("500");
        card4.setNumber("123456");
        card4.setOwner(cardHolder4);

        try{
        	Date dateFormated1 = sdf.parse("2023-02-05");
        	card4.setExpirationDate(dateFormated1);
        }
        catch (Exception e)
        {
        	throw new TarjetasException(e.getMessage());
        }
        
        System.out.println("Bancos creados exitosamente!");

        card3.setBank(bank2);
        card4.setBank(bank3);
        
		cardService.createCard(card3);
		cardService.createCard(card4);
		
		Discount discount1 = new Discount();
		discount1.setComments("Descuento en Carrefour");
		discount1.setCode("222");
		discount1.setCuitStore("123456");
		discount1.setDiscountPercentage(20);
		Date date23 = new Date();
		discount1.setValidityEndDate(date23);
		discount1.setValidityStartDate(date23);
		promotionsService.createPromotion(discount1);
			
		Discount discount2 = new Discount();
		discount2.setComments("Descuento en la Victoria");
		discount2.setCode("333");
		discount2.setCuitStore("123456");
		discount2.setDiscountPercentage(20);
		discount2.setValidityEndDate(date23);
		discount2.setValidityStartDate(date23);
		promotionsService.createPromotion(discount2);
		
        List<Promotion> listDiscount1 = new ArrayList<Promotion>();
        listDiscount1.add(discount1);
        listDiscount1.add(discount2);
        
        CashPayment cash1 = new CashPayment();
		cash1.setCuitStore("123456");
		cash1.setAmount(1234);
		cash1.setFinalAmount(1100);
		cash1.setPaymentVoucher("Carrefour");
		cash1.setStore("Carrefour");
		cash1.setPromotions(listDiscount1);
		cash1.setCard(card1);
		purchaseService.createPurchase(cash1);
		
		CashPayment cash2 = new CashPayment();
		cash2.setCuitStore("123456");
		cash2.setAmount(4324);
		cash2.setFinalAmount(4000);
		cash2.setPaymentVoucher("la Victoria");
		cash2.setStore("Victoria");
		cash2.setPromotions(listDiscount1);
		cash2.setCard(card1);
		purchaseService.createPurchase(cash2);

		List<CashPayment> listaCash = new ArrayList<>();
		listaCash.add(cash1);
		listaCash.add(cash2);
		
		Payment payment1= new Payment();
		payment1.setCode("123");
		payment1.setFirstExpiration(fecha);
		payment1.setSecondExpiration(fecha);
		payment1.setMonth("January");
		payment1.setYear("2023");
		payment1.setPurchase(3322);
		payment1.setTotalPrice(5452);
		payment1.setCashpayments(listaCash);
		paymentService.createPayment(payment1);
		
        CashPayment cash3 = new CashPayment();
        cash3.setCuitStore("123456");
        cash3.setAmount(1234);
        cash3.setFinalAmount(1100);
        cash3.setPaymentVoucher("Unimark");
        cash3.setStore("Unimark");
        cash3.setPromotions(listDiscount1);
        cash3.setCard(card2);
		purchaseService.createPurchase(cash3);
		
		CashPayment cash4 = new CashPayment();
		cash4.setCuitStore("123456");
		cash4.setAmount(4324);
		cash4.setFinalAmount(4000);
		cash4.setPaymentVoucher("Coto");
		cash4.setStore("Coto");
		cash4.setPromotions(listDiscount1);
		cash4.setCard(card3);
		purchaseService.createPurchase(cash4);
		
		List<CashPayment> listaCash2 = new ArrayList<>();
		listaCash2.add(cash3);
		listaCash2.add(cash4);

		Payment payment2 = new Payment();
		payment2.setCode("323");
		payment2.setFirstExpiration(fecha);
		payment2.setSecondExpiration(fecha);
		payment2.setMonth("January");
		payment2.setYear("2023");
		payment2.setPurchase(86465);
		payment2.setTotalPrice(3455);
		payment2.setCashpayments(listaCash2);
		paymentService.createPayment(payment2);	
		
		CashPayment cash5 = new CashPayment();
		cash5.setCuitStore("123456");
		cash5.setAmount(4324);
		cash5.setFinalAmount(4000);
		cash5.setPaymentVoucher("Dia");
		cash5.setStore("Dia");
		cash5.setPromotions(listDiscount1);
		cash5.setCard(card3);
		purchaseService.createPurchase(cash5);
		
		List<CashPayment> listaCash3 = new ArrayList<>();
		listaCash3.add(cash5);
		
		Payment payment3 = new Payment();
		payment3.setCode("535");
		payment3.setFirstExpiration(fecha);
		payment3.setSecondExpiration(fecha);
		payment3.setMonth("January");
		payment3.setYear("2023");
		payment3.setPurchase(86465);
		payment3.setTotalPrice(3455);
		payment3.setCashpayments(listaCash3);
		paymentService.createPayment(payment3);	
				
		Financing financing1 = new Financing();
		financing1.setComments("Financiamiento Plan A");
		financing1.setCode("664");
		financing1.setCuitStore("123456");
		financing1.setInteres(2);
		financing1.setNumberOfQuotas(12);
		financing1.setValidityEndDate(date23);
		financing1.setValidityStartDate(date23);
		promotionsService.createPromotion(financing1);
			
		Financing financing2 = new Financing();
		financing2.setComments("Financiamiento Plan B");
		financing2.setCode("345");
		financing2.setCuitStore("123456");
		financing2.setInteres(2);
		financing2.setNumberOfQuotas(16);
		financing2.setValidityEndDate(date23);
		financing2.setValidityStartDate(date23);
		promotionsService.createPromotion(financing2);

        List<Promotion> listDiscount2 = new ArrayList<Promotion>();
        listDiscount1.add(financing1);
        listDiscount1.add(financing2);
        
		Quota quota1 = new Quota();
		quota1.setPrice(1230);
		quota1.setMonth(payment1.getMonth());
		quota1.setYear(payment1.getYear());
		quota1.setNumber(1);
		//quotaService.createQuota(quota1);

		Quota quota2 = new Quota();
		quota2.setPrice(2345);
		quota2.setMonth(payment1.getMonth());
		quota2.setYear(payment1.getYear());
		quota2.setNumber(2);
		//quotaService.createQuota(quota2);

		Quota quota3 = new Quota();
		quota3.setPrice(3121);
		quota3.setMonth(payment1.getMonth());
		quota3.setYear(payment1.getYear());
		quota3.setNumber(3);
		//quotaService.createQuota(quota3);

		List<Quota> lista = new ArrayList<>();
		lista.add(quota1);
		lista.add(quota2);
		lista.add(quota3);
		
		//compras mensuales
		MonthlyPayments mes1 = new MonthlyPayments();
		mes1.setCuitStore("123456");
		mes1.setAmount(1500);
		mes1.setFinalAmount(1300);
		mes1.setPaymentVoucher("la anonima");
		mes1.setStore("La anonima");
		mes1.setPromotions(listDiscount2);
		mes1.setCard(card3);
		mes1.setQuotas(lista);
		purchaseService.createPurchase(mes1);	
		
		List<MonthlyPayments> listaCash4 = new ArrayList<>();
		listaCash4.add(mes1);

		MonthlyPayments mes2 = new MonthlyPayments();
		mes2.setCuitStore("123456");
		mes2.setAmount(1500);
		mes2.setFinalAmount(1300);
		mes2.setPaymentVoucher("la anonima");
		mes2.setStore("La anonima");
		mes2.setPromotions(listDiscount2);
		mes2.setCard(card3);
		purchaseService.createPurchase(mes2);
		
		List<Quota> lista2 = new ArrayList<>();
		lista2.add(quota1);
		lista2.add(quota2);
		lista2.add(quota3);
		
		Payment payment4 = new Payment();
		payment4.setCode("222");
		payment4.setFirstExpiration(fecha);
		payment4.setSecondExpiration(fecha);
		payment4.setMonth("January");
		payment4.setYear("2023");
		payment4.setPurchase(4324);
		payment4.setTotalPrice(8645);
		payment4.setQuotas(lista2);
		paymentService.createPayment(payment4);
		
		Quota quota4 = new Quota();
		quota4.setPrice(1234);
		quota4.setMonth(payment1.getMonth());
		quota4.setYear(payment1.getYear());
		quota4.setNumber(1);

		Quota quota5 = new Quota();
		quota5.setPrice(5431);
		quota5.setMonth(payment1.getMonth());
		quota5.setYear(payment1.getYear());
		quota5.setNumber(2);

		Quota quota6 = new Quota();
		quota6.setPrice(1124);
		quota6.setMonth(payment1.getMonth());
		quota6.setYear(payment1.getYear());
		quota6.setNumber(3);

		List<Quota> lista4 = new ArrayList<>();
		lista4.add(quota4);
		lista4.add(quota5);
		lista4.add(quota6);
		
		//compras mensuales
		MonthlyPayments mes3 = new MonthlyPayments();
		mes3.setCuitStore("34551344");
		mes3.setAmount(2500);
		mes3.setFinalAmount(2200);
		mes3.setPaymentVoucher("Difcor");
		mes3.setStore("Difcor");
		mes3.setPromotions(listDiscount2);
		mes3.setCard(card4);
		mes3.setQuotas(lista4);
		purchaseService.createPurchase(mes3);	
		
		List<MonthlyPayments> listaCash5 = new ArrayList<>();
		listaCash5.add(mes3);

		MonthlyPayments mes4 = new MonthlyPayments();
		mes4.setCuitStore("4156143");
		mes4.setAmount(1234);
		mes4.setFinalAmount(1009);
		mes4.setPaymentVoucher("La Mendocina");
		mes4.setStore("La Mendocina");
		mes4.setPromotions(listDiscount2);
		mes4.setCard(card4);
		purchaseService.createPurchase(mes4);
	
		Payment payment6 = new Payment();
		payment6.setCode("222");
		payment6.setFirstExpiration(fecha);
		payment6.setSecondExpiration(fecha);
		payment6.setMonth("January");
		payment6.setYear("2023");
		payment6.setPurchase(2345);
		payment6.setTotalPrice(7345);
		payment6.setQuotas(lista4);
		paymentService.createPayment(payment6);
		
		System.out.println("Pagos creados exitosamente!");

	}

	public void crearPagos() throws TarjetasException {

		Date fecha = new Date();

		Payment payment1 = new Payment();
		payment1.setCode("212");
		payment1.setFirstExpiration(fecha);
		payment1.setSecondExpiration(fecha);
		payment1.setMonth("January");
		payment1.setYear("2023");
		payment1.setPurchase(1230);
		payment1.setTotalPrice(12000);
		
		Quota quota1 = new Quota();
		quota1.setPrice(1230);
		quota1.setMonth(payment1.getMonth());
		quota1.setYear(payment1.getYear());
		quota1.setNumber(1);

		Quota quota2 = new Quota();
		quota2.setPrice(1230);
		quota2.setMonth(payment1.getMonth());
		quota2.setYear(payment1.getYear());
		quota2.setNumber(2);
		
		List<Quota> lista = new ArrayList<>();
		lista.add(quota1);
		lista.add(quota2);

		payment1.setQuotas(lista);
		paymentService.createPayment(payment1);
		//quotaService.createQuota(quota1);

		Payment payment2 = new Payment();
		payment2.setCode("212");
		payment2.setFirstExpiration(fecha);
		payment2.setSecondExpiration(fecha);
		payment2.setMonth("January");
		payment2.setYear("2023");
		payment2.setPurchase(1230);
		payment2.setTotalPrice(12000);
		paymentService.createPayment(payment2);
      


	}
	
	public void crearPunto1() throws TarjetasException {

		Discount discount1 = new Discount();
		discount1.setComments("Descuento en la Anonima");
		discount1.setCode("212");
		discount1.setCuitStore("123456");
		discount1.setDiscountPercentage(10);
		Date date23 = new Date();
		discount1.setValidityEndDate(date23);
		discount1.setValidityStartDate(date23);
		//no es necesario persistir porq ya persiste cuando crea el BANCO
		//promotionsService.createPromotion(discount1);
		
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        promotionsList.add(discount1);
        
		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
		bank1.setPromotions(promotionsList);
		bankService.createBank(bank1);
		
		tarjetasService.addNewPromotion(discount1, bank1.getId());
     
		System.out.println("Promocion Descuento agregada exitosamente!");

	}
	
	public void crearPurchases() throws TarjetasException {

		
		Date fecha = new Date();

		Payment payment1 = new Payment();
		payment1.setCode("212");
		payment1.setFirstExpiration(fecha);
		payment1.setSecondExpiration(fecha);
		payment1.setMonth("January");
		payment1.setYear("2023");
		payment1.setPurchase(2322);
		payment1.setTotalPrice(5344);
		
		Quota quota1 = new Quota();
		quota1.setPrice(1230);
		quota1.setMonth(payment1.getMonth());
		quota1.setYear(payment1.getYear());
		quota1.setNumber(1);

		Quota quota2 = new Quota();
		quota2.setPrice(2323);
		quota2.setMonth(payment1.getMonth());
		quota2.setYear(payment1.getYear());
		quota2.setNumber(2);

		
		List<Quota> listaQuotas = new ArrayList<>();
		listaQuotas.add(quota1);
		listaQuotas.add(quota2);

		payment1.setQuotas(listaQuotas);
		paymentService.createPayment(payment1);
		//quotaService.createQuota(quota1);

		Payment payment2 = new Payment();
		payment2.setCode("212");
		payment2.setFirstExpiration(fecha);
		payment2.setSecondExpiration(fecha);
		payment2.setMonth("January");
		payment2.setYear("2023");
		payment2.setPurchase(1230);
		payment2.setTotalPrice(12000);
		
		Quota quota3 = new Quota();
		quota3.setPrice(3233);
		quota3.setMonth(payment1.getMonth());
		quota3.setYear(payment1.getYear());
		quota3.setNumber(1);

		Quota quota4 = new Quota();
		quota4.setPrice(1124);
		quota4.setMonth(payment1.getMonth());
		quota4.setYear(payment1.getYear());
		quota4.setNumber(2);

		
		List<Quota> listaQuotas1 = new ArrayList<>();
		listaQuotas1.add(quota3);
		listaQuotas1.add(quota4);
		
		payment2.setQuotas(listaQuotas1);
		paymentService.createPayment(payment2);

		
		MonthlyPayments monthly1 = new MonthlyPayments();
		monthly1.setCuitStore("123456");
		monthly1.setAmount(1234);
		monthly1.setFinalAmount(54321);
		monthly1.setInterest(18);
		monthly1.setNumberOfQuotas(12);
		monthly1.setQuotas(listaQuotas);
		purchaseService.createPurchase(monthly1);
		
//		quota1.setMontlypayment(monthly1);
//		quota2.setMontlypayment(monthly1);
		quotaService.createQuota(quota2);
		quotaService.createQuota(quota1);

		System.out.println("Purchases creadas exitosamente!");

	}
	
	public void crearTitulares() throws TarjetasException {

		CardHolder cardHolder1 = new CardHolder();
		cardHolder1.setCompleteName("Abramcito");
		cardHolder1.setDni("123456");
		cardHolder1.setTelephone("654321");
		cardHolder1.setCuil("11-4681561-8");
		cardHolderService.createCardHolder(cardHolder1);
		
        CardHolder cardHolder2 = new CardHolder();
		cardHolder2.setCompleteName("Pablito");
		cardHolder2.setDni("345673546");
		cardHolder2.setTelephone("34561234");
		cardHolder2.setCuil("11-1213454-8");
		cardHolderService.createCardHolder(cardHolder2);
     
		System.out.println("titulares agregados exitosamente!");
	}

	public void crearBancos2() throws TarjetasException {
		Discount discount1 = new Discount();
		discount1.setComments("Descuento en la Anonima");
		discount1.setCode("212");
		discount1.setCuitStore("123456");
		discount1.setDiscountPercentage(10);
		Date date23 = new Date();
		discount1.setValidityEndDate(date23);
		discount1.setValidityStartDate(date23);
		//no es necesario persistir porq ya persiste cuando crea el BANCO
		//promotionsService.createPromotion(discount1);

		List<Promotion> promotionsList = new ArrayList<Promotion>();
		promotionsList.add(discount1);
		//promotionsService.createPromotion(discount1);

		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
        bank1.setPromotions(promotionsList);
		bankService.createBank(bank1);

		Bank bank2 = new Bank();
		bank2.setName("Banco Galicia");
		bank2.setAddress("San Martin 1234");
		bank2.setCuit("20-1245454-2");
		bank2.setTelephone("98765412");
		bankService.createBank(bank2);


		
    	System.out.println("Bancos creados exitosamente!");

	}
	
	public void crearCards() throws TarjetasException {

        //creo tarjetas y le seteo el banco
		Card card1 = new Card();
        card1.setCardholderNameInCard("Abramcito");
        card1.setCcv("500");
        card1.setNumber("123456");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try{
		Date dateFormated1 = sdf.parse("2023-02-05");
		card1.setExpirationDate(dateFormated1);
		}
		catch (Exception e)
		{
			throw new TarjetasException(e.getMessage());
		}
		
        Card card2 = new Card();
        card2.setCardholderNameInCard("Pablito");
        card2.setCcv("500");
        card2.setNumber("123456");
		//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try{
		Date dateFormated1 = sdf.parse("2023-02-02");
		card2.setExpirationDate(dateFormated1);
		}
		catch (Exception e)
		{
			throw new TarjetasException(e.getMessage());
		}

		cardService.createCard(card1);

		cardService.createCard(card2);

		
    	System.out.println("Cards creados exitosamente!");

	}

	public void crearCashyPromos() throws TarjetasException {

		
		//tarjetasService.addNewPromotion(discount1, bank1.getId());
     
    	System.out.println("cash y promos creados exitosamente!");

	}
}
