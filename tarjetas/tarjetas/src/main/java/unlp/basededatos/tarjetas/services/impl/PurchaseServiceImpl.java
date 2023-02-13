package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.repositories.PurchaseRepository;
import unlp.basededatos.tarjetas.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository repository;

    @Override
    @Transactional
    public Purchase createPurchase(Purchase purchase) throws TarjetasException {
        return this.repository.save(purchase);
    }

    @Override
    @Transactional
    public Purchase getPurchase(Long id) throws TarjetasException {
        return this.repository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Purchase updatePurchase(Purchase purchase, Long id) throws TarjetasException  {
        Purchase purchase1 =  this.repository.getReferenceById(id);
        purchase1.setStore(purchase.getStore());
        purchase1.setPaymentVoucher(purchase.getPaymentVoucher());
        this.repository.save(purchase1);
        return purchase1;
    }

}
