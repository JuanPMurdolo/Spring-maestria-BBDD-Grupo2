package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.repositories.PurchaseRepository;
import unlp.basededatos.tarjetas.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository repository;

    @Override
    public Purchase createPurchase(Purchase purchase) throws TarjetasException {
        return this.repository.save(purchase);
    }

    @Override
    public Optional<Purchase> getPurchase(String id) throws TarjetasException {
        return this.repository.findById(id);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase, String id) throws TarjetasException  {
        Purchase purchase1 = this.repository.findById(id).orElse(null);
        purchase1.setStore(purchase.getStore());
        purchase1.setPaymentVoucher(purchase.getPaymentVoucher());
        this.repository.save(purchase1);
        return purchase1;
    }

}
