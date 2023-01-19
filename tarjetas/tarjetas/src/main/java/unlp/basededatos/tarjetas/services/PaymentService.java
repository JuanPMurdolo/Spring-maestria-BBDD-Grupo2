package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface PaymentService {
    public Payment createPayment(Payment payment) throws TarjetasException;
}
