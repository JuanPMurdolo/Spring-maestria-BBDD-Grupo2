package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment createPayment(Payment payment) throws TarjetasException {
        Long id = this.paymentRepository.savePayment(payment);
        return this.paymentRepository.findPaymentById(id);
    }
}
