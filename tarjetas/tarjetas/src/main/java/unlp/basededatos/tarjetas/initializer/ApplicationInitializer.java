package unlp.basededatos.tarjetas.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.services.BanksService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Component
public class ApplicationInitializer implements CommandLineRunner {
    
	
	@Autowired
	BanksService bankService;

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
        
		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
		bank1.setCards(cardsList);
		
        card1.setBank(bank1);
        card2.setBank(bank1);

		bankService.createBank(bank1);

		Bank bank2 = new Bank();
		bank2.setName("Banco Galicia");
		bank2.setAddress("San Martin 1234");
		bank2.setCuit("20-1245454-2");
		bank2.setTelephone("98765412");
		bankService.createBank(bank2);

		Bank bank3 = new Bank();
		bank3.setName("Banco Hipotecario");
		bank3.setAddress("San Martin 1234");
		bank3.setCuit("20-1245454-2");
		bank3.setTelephone("98765412");
		bankService.createBank(bank3);

		Bank bank4 = new Bank();
		bank4.setName("Banco Patagonia");
		bank4.setAddress("San Martin 1234");
		bank4.setCuit("20-1245454-2");
		bank4.setTelephone("98765412");
		bankService.createBank(bank4);

		System.out.println("Bancos creados exitosamente!");

	}

}
