package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.services.PaymentService;
import unlp.basededatos.tarjetas.utils.PaymentDTO;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<ArrayList> getTotalCashByMonth(@PathVariable String month) throws TarjetasException {
        try {
            return this.paymentService.getTotalCashByMonth(month);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }

    }
    
    @GetMapping(path = "/getTotalCashByMonth2/{month}")
    public List<ArrayList> getTotalCashByMonth2(@PathVariable String month) throws TarjetasException {
        try {
            return this.paymentService.getTotalCashByMonth2(month);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }

    }
    
    
    @GetMapping(path = "/getTotalQuotasByMonth/{month}")
    public List<ArrayList> getTotalQuotasByMonth(@PathVariable String month) throws TarjetasException {
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

    		return this.tarjetaService.getTotalByMonth(month);
    	}
    	catch (Exception e) {
    		throw new TarjetasException(e.getMessage());
    	}
    }
    

    @GetMapping(path = "/getStoreWithMostSales/{month}")
    public List<ArrayList> getStoreWithMostSales(@PathVariable String month)throws TarjetasException{
        try {
            return this.tarjetaService.getInfoFromBusiness(month);
            }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    
}
