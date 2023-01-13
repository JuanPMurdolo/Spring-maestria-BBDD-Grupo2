package unlp.basededatos.tarjetas.controllers;

import unlp.basededatos.tarjetas.utils.BankException;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.services.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/bank")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BanksController {

    @Autowired
    private BanksService service;

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
    public Bank createBank(@RequestBody Bank bank) throws BankException {
        return this.service.createBank(bank);
    }

    /*
    POST /testCreate
    Es para probar : recibe dos strings
    Ejemplo:
     		localhost:8080/bank/testCreate?name=juan&mesage=holis
     */
    
    @PostMapping(path = "/testCreate")
    public String create(String name, String mesage) throws BankException {
    	Bank bank = new Bank();
    	Random r = new Random();
    	int randomId = r.nextInt(Integer.MAX_VALUE);
    	bank.setId((long) randomId);
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
    public Bank getBank(@PathVariable Long id) throws BankException {
        return this.service.getBank(id);
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
    public Bank updateBank(@RequestBody Bank bank, @PathVariable Long id) throws BankException{
        return this.service.updateBank(bank,id);
    }

}
