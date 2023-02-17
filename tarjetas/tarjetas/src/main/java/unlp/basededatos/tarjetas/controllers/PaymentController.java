package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.services.PaymentService;
import unlp.basededatos.tarjetas.services.PromotionsService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Payment")
public class PaymentController {
	
    @Autowired
    private ITarjetasService tarjetaService;
    
    @Autowired
    private PaymentService paymentService;
    
    @PutMapping(path = "/UpdatePaymentExpiration/{code}")
    public List<Payment> updatePaymentExpiration(@PathVariable String code, @RequestBody Map<String, String> json) throws TarjetasException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormated1 = formatter.parse(json.get("date1").toString());
            Date dateFormated2 = formatter.parse(json.get("date2").toString());
            return this.tarjetaService.updatePaymentsExpiration(code, dateFormated1, dateFormated2);
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }
    
    @GetMapping(path = "/getTotalCashByMonth/{month}")
    public float getTotalCashByMonth(@PathVariable String month) throws TarjetasException {
        try {
            return this.paymentService.getTotalCashByMonth(month);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }

    }
    
    @GetMapping(path = "/getTotalQuotasByMonth/{month}")
    public float getTotalQuotasByMonth(@PathVariable String month) throws TarjetasException {
        try {
            return this.paymentService.getTotalQuotasByMonth(month);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }

    }
    
    @GetMapping(path = "/getTotalByMonth/{month}")
    public String getTotalByMonth(@PathVariable String month) throws TarjetasException {
        try {
        	
			  float totalMonthly = this.paymentService.getTotalQuotasByMonth(month); float
			  totalCash = this.paymentService.getTotalCashByMonth(month);
			  
			  Float total = Float.sum(totalMonthly,totalCash);
			  
			  Map<String, Float> elements = new HashMap<String, Float>();
			  elements.put("TOTAL        :", total); 
			  elements.put("Total Cash   :", totalCash); 
			  elements.put("Total Monthly:", totalMonthly);
			  
			  ObjectMapper objectMapper = new ObjectMapper();
			  
			  String json = objectMapper.writeValueAsString(elements);
			  System.out.println(json);
			  
			  return json;
			 
            //return this.tarjetaService.getTotalByMonth(month);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }

    }
}
