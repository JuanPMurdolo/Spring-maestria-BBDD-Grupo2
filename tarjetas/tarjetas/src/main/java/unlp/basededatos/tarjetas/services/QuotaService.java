package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.Quota;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface QuotaService {

    public Quota createQuota(Quota quota) throws TarjetasException;
    public String create(String name, String message) throws TarjetasException;

    public Quota getQuota(Long id) throws TarjetasException;

    public Quota updateQuota(Quota quota,Long id) throws TarjetasException;

}
