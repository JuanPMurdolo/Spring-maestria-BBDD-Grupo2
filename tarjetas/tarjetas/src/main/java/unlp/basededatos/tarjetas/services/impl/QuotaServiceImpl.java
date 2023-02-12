package unlp.basededatos.tarjetas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unlp.basededatos.tarjetas.model.Quota;
import unlp.basededatos.tarjetas.repositories.QuotaRepository;
import unlp.basededatos.tarjetas.services.QuotaService;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Service
public class QuotaServiceImpl implements QuotaService{

    @Autowired
    private QuotaRepository repository;

    @Override
    @Transactional
    public Quota createQuota(Quota quota) throws TarjetasException {
        Long id = this.repository.saveQuota(quota);
        return this.repository.findQuotaById(id);
    }

    @Override
    @Transactional
    public String create(String name, String message) throws TarjetasException {
        return null;
    }

    @Override
    @Transactional
    public Quota getQuota(Long id) throws TarjetasException {
        return this.repository.findQuotaById(id);
    }

    @Override
    @Transactional
    public Quota updateQuota(Quota quota, Long id) throws TarjetasException {
        Quota quota1 = this.repository.findQuotaById(id);
        quota1.setNumber(quota.getNumber());
        quota1.setPrice(quota.getPrice());
        quota1.setMonth(quota.getMonth());
        quota1.setYear(quota.getYear());
        quota1.setPayment(quota.getPayment());
        return quota1;
    }
}
