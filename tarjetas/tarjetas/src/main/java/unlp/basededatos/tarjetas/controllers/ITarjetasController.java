package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/ITarjetas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ITarjetasController {

    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }


}
