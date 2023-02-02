package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Payment")
public class PaymentController {
    @Autowired
    private ITarjetasService service;
    @PutMapping(path = "/UpdatePaymentExpiration/{code}")
    public List<Payment> updatePaymentExpiration(@PathVariable String code, @RequestBody Map<String, String> json) throws TarjetasException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFormated1 = formatter.parse(json.get("date1").toString());
            Date dateFormated2 = formatter.parse(json.get("date2").toString());
            return this.service.updatePaymentsExpiration(code, dateFormated1, dateFormated2);
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }

    @GetMapping(path = "/getQuotasTotal/{id}")
    public float getQuotasTotal(@PathVariable Long id) throws TarjetasException {
        try {
            return this.service.totalQuota(id);
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }
}
