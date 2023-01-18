package unlp.basededatos.tarjetas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.services.ITarjetasService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@RestController
@RequestMapping(value = "/ITarjetas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ITarjetasController {
    @Autowired
    private ITarjetasService service;

    @GetMapping(path = "/testing")
    public String prueba() {
        return "OK";
    }
}
