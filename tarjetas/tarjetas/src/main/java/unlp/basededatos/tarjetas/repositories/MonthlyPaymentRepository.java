package unlp.basededatos.tarjetas.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.MonthlyPayments;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Repository
public class MonthlyPaymentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public MonthlyPayments findMonthlyPaymentById(Long id) throws TarjetasException {
        try {
            return (MonthlyPayments) this.sessionFactory.getCurrentSession().createQuery("from MonthlyPayments where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }
}
