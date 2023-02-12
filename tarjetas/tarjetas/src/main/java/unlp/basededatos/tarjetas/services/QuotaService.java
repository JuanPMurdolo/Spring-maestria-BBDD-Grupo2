package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.Quota;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Optional;

public interface QuotaService {

    public void createQuota(Quota quota) throws TarjetasException;
    public Optional<Quota> getQuota(Long id) throws TarjetasException;

}
