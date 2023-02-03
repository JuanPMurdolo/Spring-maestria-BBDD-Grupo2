package unlp.basededatos.tarjetas.controllers;

import org.springframework.http.HttpStatus;

import unlp.basededatos.tarjetas.utils.Response;

public abstract class BaseController {

    protected HttpStatus responseStatus(Response response){
        return HttpStatus.valueOf(response.getStatus().getCode());
    }

}
