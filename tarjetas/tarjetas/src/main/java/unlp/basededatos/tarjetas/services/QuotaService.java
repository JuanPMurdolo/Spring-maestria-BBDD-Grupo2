package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.Quota;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Optional;

public interface QuotaService {

    void createQuota(Quota quota) throws TarjetasException;
    Optional<Quota> getQuota(String id) throws TarjetasException;

}
