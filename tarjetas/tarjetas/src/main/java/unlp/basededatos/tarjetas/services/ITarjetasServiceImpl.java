package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ITarjetasServiceImpl implements ITarjetasService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException {
        //obtener todas los payments que se correspondan con el code
        List<Payment> lista = this.paymentRepository.findPaymentsByCode(code);
        for (int i = 0; i < lista.size(); i++) {
            //Leer el payment en dicha posicion
            Payment payment1 = lista.get(i);
            //se modifica la fecha de expiracion
            payment1.setFirstExpiration(first);
            payment1.setSecondExpiration(second);
            //Se actualiza el payment
            this.paymentRepository.updatePayment(payment1);
        }
        return lista;
    }
}
