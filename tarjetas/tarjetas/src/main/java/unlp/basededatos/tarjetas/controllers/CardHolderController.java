package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.services.CardHolderService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cardHolder")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CardHolderController {

	@Autowired
	private CardHolderService service;

	/*
        Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
	 */
	@GetMapping(path = "/testing")
	public String prueba() {
		return "OK";
	}

	/*
        GET /id/{id}
        Obtiene los datos de la Real State con id indicado en la URL
        ejemplo:
                 localhost:8080/bank/id/1

	 */
	@GetMapping(path = "/id/{id}")
	public Optional<CardHolder> getCardHolder(@PathVariable Long id) throws TarjetasException {
		return this.service.getCardHolder(id);
	}

    // 9-Obtener los titulares de las 10 tarjetas con más compras.
    @GetMapping("/10-titulares-con-mas-compras")
    public List<CardHolder> getTop10SupplierConMasOrdenesDespachadas() throws TarjetasException { 
    	List<CardHolder> cardHolders = this.service.get10CardHolersWithMorePurchases();
    	return cardHolders;
    }


}

