package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.MonthlyPayments;
import unlp.basededatos.tarjetas.repositories.MonthlyPaymentRepository;
import unlp.basededatos.tarjetas.services.MonthlyPaymentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentsService{

    @Autowired
    private MonthlyPaymentRepository repository;

    @Override
    public MonthlyPayments createMonthlyPayments(MonthlyPayments monthlyPayment) throws TarjetasException {
        return this.repository.save(monthlyPayment);
    }

    @Override
    public Optional<MonthlyPayments> getMonthlyPayments(String id) throws TarjetasException {
        Optional <MonthlyPayments> optional = Optional.ofNullable(this.repository.findById(id).orElse(null));
        return optional;
    }

    @Override
    public MonthlyPayments updateMonthlyPayments(MonthlyPayments monthlyPayment, String id) throws TarjetasException  {
        MonthlyPayments monthlyPayment1 = this.repository.findById(id).orElse(null);
        monthlyPayment1.setStore(monthlyPayment.getStore());
        monthlyPayment1.setPaymentVoucher(monthlyPayment.getPaymentVoucher());
        this.repository.save(monthlyPayment1);
        return monthlyPayment1;
    }

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);

	}

}
