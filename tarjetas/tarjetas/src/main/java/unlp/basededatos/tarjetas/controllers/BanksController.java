package unlp.basededatos.tarjetas.controllers;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Financing;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.services.BanksService;
import unlp.basededatos.tarjetas.services.ITarjetasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(value = "/bank")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BanksController {

    @Autowired
    private BanksService service;
    
    @Autowired
    private ITarjetasService serviceTarjetas;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }

    /*
    POST /testCreate
    Es para probar : recibe dos strings
    Ejemplo:
     		localhost:8080/bank/testCreate?name=juan&mesage=holis
     */
    
    @PostMapping(path = "/testCreate")
    public String create(String name, String mesage) throws TarjetasException {
    	Bank bank = new Bank();
    	Random r = new Random();
    	bank.setAddress(mesage);
    	bank.setName(name);
    	bank.setCuit(mesage);
    	bank.setTelephone(name);
        this.service.createBank(bank);
        return "Banco creado exitosamente!";
    }
    
    /*
    GET /id/{id}
    Obtiene los datos de la Real State con id indicado en la URL
    ejemplo:
             localhost:8080/bank/id/1
    
     */
    @GetMapping(path = "/id/{id}")
    public Optional<Bank> getBank(@PathVariable String id) throws TarjetasException {
        return this.service.getBank(id);
    }

   
	/*
	 * @PostMapping("/addNewDiscount") public long addNewDiscount( @RequestBody
	 * Discount discount ) { try { discount = service.addNewPromotion(discount);
	 * return (discount != null) ? discount.getId() : -1; } catch (Exception e) {
	 * e.printStackTrace(); return -1; } }
	 * 
	 * @PostMapping("/addNewFinancing") public long addNewFinancing( @RequestBody
	 * Financing financing ) { try { financing = service.addNewPromotion(financing);
	 * return (financing != null) ? financing.getId() : -1; } catch (Exception e) {
	 * e.printStackTrace(); return -1; } }
	 */
	
	@PostMapping("/addPromotion/{id}")
	public String addNewDiscount( @RequestBody Promotion promotion, @PathVariable String id ) {
    	try {
    		promotion = serviceTarjetas.addNewPromotion(promotion,id);
    		return (promotion != null) ? promotion.getId() : null;
		}
    	catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
    @GetMapping(path = "/getBankMostImportByCard")
    public Bank getBankMostImportByCard() throws TarjetasException {
    	try { 
    		return this.serviceTarjetas.getBankMostImportByCard();
    	}
    	catch (Exception e) {
    		throw new TarjetasException(e.getMessage());
    	}
    }
    
	
}
