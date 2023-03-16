package unlp.basededatos.tarjetas.controllers;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Product;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Financing;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.services.ProductService;
import unlp.basededatos.tarjetas.services.ITarjetasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    @Autowired
    private ProductService service;

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
             localhost:8080/product/id/1
    
     */
    @GetMapping(path = "/id/{id}")
    public Optional<Product> getProduct(@PathVariable String id) throws TarjetasException {
        return this.service.getProduct(id);
    }

   
	
    @GetMapping(path = "/getCantidades")
    public Product getProductMostImportByCard() throws TarjetasException {
    	try { 

			System.out.println("Sum Quantities: " + service.sumQuantities());
			System.out.println("Sum Quantities with Condition: " + service.sumQuantitiesWithCondition(true));
			System.out.println("Total: " + service.total(true));
			
			System.out.println("The smallest price: " + service.min());
			System.out.println("The biggest price: " + service.max());
			
			long result1 = service.count1();
			System.out.println("Count 1: " + result1);

			long result2 = service.count2(true);
			System.out.println("Count 2: " + result2);
			
			
			}
    	catch (Exception e) {
    		throw new TarjetasException(e.getMessage());
    	}
		return null;
    }
    
	
}
