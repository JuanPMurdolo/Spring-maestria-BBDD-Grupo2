package unlp.basededatos.tarjetas.initializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.services.BanksService;
import unlp.basededatos.tarjetas.services.CardHolderService;
import unlp.basededatos.tarjetas.services.PaymentService;
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

	////////
	// URL Swagger: http://localhost:8081/swagger-ui/index.html
	////////

	public void run(String... args) throws Exception {

		crearBancos();

	}

	public void crearBancos() throws TarjetasException {

        //creo tarjetas y le seteo el banco
		Card card1 = new Card();
        card1.setCardholderNameInCard("Abramcito");
        card1.setCcv("500");
        card1.setNumber("123456");

        Card card2 = new Card();
        card2.setCardholderNameInCard("Pablito");
        card2.setCcv("500");
        card2.setNumber("123456");
        
        List<Card> cardsList = new ArrayList<Card>();
        cardsList.add(card1);
        cardsList.add(card2);

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

        List<CardHolder> cardHoldersList = new ArrayList<CardHolder>();
        cardHoldersList.add(cardHolder1);
        cardHoldersList.add(cardHolder2);
        
		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
		bank1.setCards(cardsList);
        bank1.setCardHolders(cardHoldersList);

        card1.setBank(bank1);
        card2.setBank(bank1);

        
		bankService.createBank(bank1);

		Bank bank2 = new Bank();
		bank2.setName("Banco Galicia");
		bank2.setAddress("San Martin 1234");
		bank2.setCuit("20-1245454-2");
		bank2.setTelephone("98765412");
		bankService.createBank(bank2);
		

        
        List<Bank> banksList = new ArrayList<Bank>();
        banksList.add(bank1);
        banksList.add(bank2);

		Date fecha = new Date();


		Payment payment1 = new Payment();
		payment1.setCode("212");
		payment1.setFirstExpiration(fecha);
		payment1.setSecondExpiration(fecha);
		payment1.setMonth("January");
		payment1.setYear("2023");
		payment1.setPurchase(1230);
		payment1.setTotalPrice(12000);
		paymentService.createPayment(payment1);

		Payment payment2 = new Payment();
		payment2.setCode("212");
		payment2.setFirstExpiration(fecha);
		payment2.setSecondExpiration(fecha);
		payment2.setMonth("January");
		payment2.setYear("2023");
		payment2.setPurchase(1230);
		payment2.setTotalPrice(12000);
		paymentService.createPayment(payment2);

        

        

		System.out.println("Bancos creados exitosamente!");

	}

}
