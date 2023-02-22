package unlp.basededatos.tarjetas.controllers;

import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/purchase")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @Autowired
    private ITarjetasService iTarjetasService;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }

    /*
    POST /purchase
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
    public Purchase createPurchase(@RequestBody Purchase purchase) throws TarjetasException {
        return this.service.createPurchase(purchase);
    }

    /*
    POST /testCreate
    Es para probar : recibe dos strings
    Ejemplo:
     		localhost:8080/purchase/testCreate?name=juan&mesage=holis
     */
    /*
    @PostMapping(path = "/testCreate")
    public String create(String name, String mesage) throws TarjetasException {
    	Purchase purchase = new Purchase();
    	Random r = new Random();
    	int randomId = r.nextInt(Integer.MAX_VALUE);
    	purchase.setId((long) randomId);
    	purchase.setAddress(mesage);
    	purchase.setName(name);
    	purchase.setCuit(mesage);
    	purchase.setTelephone(name);
        this.service.createPurchase(purchase);
        return "Banco creado exitosamente!";
    }*/
    
    /*
    GET /id/{id}
    Obtiene los datos de la Real State con id indicado en la URL
    ejemplo:
             localhost:8080/purchase/id/1
    
     */
    @GetMapping(path = "/id/{id}")
    public Purchase getPurchase(@PathVariable Long id) throws TarjetasException {
        return this.service.getPurchase(id);
    }

    /*
    PUT /purchase
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
    public Purchase updatePurchase(@RequestBody Purchase purchase, @PathVariable Long id) throws TarjetasException{
        return this.service.updatePurchase(purchase,id);
    }

    @GetMapping(path = "/purchaseFullInfo/{id}")
    public String purchaseFullInfo(@PathVariable Long id) throws TarjetasException{
        try {
            return this.iTarjetasService.getPurchaseInfo(id);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    @GetMapping(path = "/getStoreWithMostSales/")
    public String getStoreWithMostSales(@RequestBody Map<String, String> json)throws TarjetasException{
        String month = json.get("month").toString();
        String type = json.get("type").toString();
        try {
            return this.iTarjetasService.getInfoFromBusiness(month, type);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    @GetMapping(path = "/getQuotasTotal/{id}")
    public float getQuotasTotal(@PathVariable Long id) throws TarjetasException {
        try {
            return this.iTarjetasService.totalQuota(id);
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }

}
