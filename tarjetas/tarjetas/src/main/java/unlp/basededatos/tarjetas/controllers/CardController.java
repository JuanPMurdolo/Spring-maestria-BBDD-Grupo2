package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.services.CardService;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/card")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CardController {

    @Autowired
    private CardService service;

    @Autowired
    private ITarjetasService tarjetasService;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }

    /*
    POST /bank
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
    public Card createCard(@RequestBody Card card) throws TarjetasException {
        return this.service.createCard(card);
    }

    /*
    POST /testCreate
    Es para probar : recibe dos strings
    Ejemplo:
     		localhost:8080/bank/testCreate?name=juan&mesage=holis
     */

    @PostMapping(path = "/testCreate")
    public String create(String name, String mesage) throws TarjetasException {
        Card card = new Card();
        Random r = new Random();
        int randomId = r.nextInt(Integer.MAX_VALUE);
        this.service.createCard(card);
        return "Tarjeta creado exitosamente!";
    }

    /*
    GET /id/{id}
    Obtiene los datos de la Real State con id indicado en la URL
    ejemplo:
             localhost:8080/bank/id/1

     */
    @GetMapping(path = "/id/{id}")
    public Card getCard(@PathVariable Long id) throws TarjetasException {
        return this.service.getCard(id);
    }

    /*
    PUT /bank
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
    public Card updateCard(@RequestBody Card card, @PathVariable Long id) throws TarjetasException{
        return this.service.updateCard(card,id);
    }

    @GetMapping(path="/listExpireSoon/")
    public List<Card> listExpireSoon() throws TarjetasException {
        return this.tarjetasService.getCardSoonExpiration();
    }


}
