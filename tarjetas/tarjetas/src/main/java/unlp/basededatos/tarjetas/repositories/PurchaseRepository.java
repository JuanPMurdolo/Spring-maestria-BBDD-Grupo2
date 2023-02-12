package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Repository
public class PurchaseRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Long savePurchase(Purchase purchase) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(purchase);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public Purchase findPurchaseById(Long id) throws TarjetasException {
        try {
            return (Purchase) this.sessionFactory.getCurrentSession().createQuery("from Purchase where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updatePurchase(Purchase purchase) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(purchase);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }
}