package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.CashPayment;
import unlp.basededatos.tarjetas.repositories.CashPaymentRepository;
import unlp.basededatos.tarjetas.services.CashPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashPaymentServiceImpl implements CashPaymentService{

    @Autowired
    private CashPaymentRepository repository;

    @Override
    public CashPayment createCashPayment(CashPayment cashPayment) throws TarjetasException {
        return this.repository.save(cashPayment);
    }

    @Override
    public Optional<CashPayment> getCashPayment(String id) throws TarjetasException {
        return this.repository.findById(id);
    }

    @Override
    public CashPayment updateCashPayment(CashPayment cashPayment, String id) throws TarjetasException  {
        CashPayment cashPayment1 = this.repository.findById(id).orElse(null);
        cashPayment1.setStore(cashPayment.getStore());
        cashPayment1.setPaymentVoucher(cashPayment.getPaymentVoucher());
        this.repository.save(cashPayment1);
        return cashPayment1;
    }

}
