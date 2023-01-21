package unlp.basededatos.tarjetas.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.List;

@Repository
public class PaymentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Long savePayment(Payment payment) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(payment);
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }

    public Payment findPaymentById(Long id) throws TarjetasException{
        try {
            return (Payment) this.sessionFactory.getCurrentSession().createQuery("from Payment where id = :id").setParameter("id", id).uniqueResult();
        }
        catch (Exception e){
            throw new TarjetasException(e.getMessage());
        }
    }
    public List<Payment> findPaymentsByCode(String code) throws TarjetasException{
        try{
            return (List<Payment>) this.sessionFactory.getCurrentSession().createQuery("from Payment where code = :code").setParameter("code", code).getResultList();
        }   catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updatePayment(Payment payment) throws TarjetasException{
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(payment);
        }
        catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }
}
