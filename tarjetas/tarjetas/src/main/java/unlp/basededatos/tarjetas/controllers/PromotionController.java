package unlp.basededatos.tarjetas.controllers;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.services.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/promotion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PromotionController {

    @Autowired
    private PromotionsService service;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }

    /*
    POST /promotion
    Recibe en el body un objeto Real State y lo persiste en la base de datos
    Ejemplo:
    {
        "name": "banco 3",
        "cuit": "542345656565",
        "address": "Calle 3 n890",
        "telephone": "30207867861"
    }
     */
    @PostMapping(path = "/create")
    public Promotion createPromotion(@RequestBody Promotion promotion) throws TarjetasException {
        return this.service.createPromotion(promotion);
    }

    /*
    POST /testCreate
    Es para probar : recibe dos strings
    Ejemplo:
     		localhost:8080/promotion/testCreate?name=juan&mesage=holis
     */
    /*
    @PostMapping(path = "/testCreate")
    public String create(String name, String mesage) throws TarjetasException {
    	Promotion promotion = new Promotion();
    	Random r = new Random();
    	int randomId = r.nextInt(Integer.MAX_VALUE);
    	promotion.setId((long) randomId);
    	promotion.setAddress(mesage);
    	promotion.setName(name);
    	promotion.setCuit(mesage);
    	promotion.setTelephone(name);
        this.service.createPromotion(promotion);
        return "Banco creado exitosamente!";
    }*/
    
    /*
    GET /id/{id}
    Obtiene los datos de la Real State con id indicado en la URL
    ejemplo:
             localhost:8080/promotion/id/1
    
     */
    @GetMapping(path = "/id/{id}")
    public Promotion getPromotion(@PathVariable Long id) throws TarjetasException {
        return this.service.getPromotion(id);
    }

    /*
    PUT /promotion
    Actualiza los datos de una Real State, debe contener todos los datos de esta.
    Ejemplo:
    {
        "name": "Inmobiliaria 2",
        "cuil": "30207867861",
        "number": "542345656565",
        "address": "Calle 3 n880",
        "numberOfEmployees": 0,
    }
     */
    @PutMapping(path = "/update/{id}")
    public Promotion updatePromotion(@RequestBody Promotion promotion, @PathVariable Long id) throws TarjetasException{
        return this.service.updatePromotion(promotion,id);
    }

}