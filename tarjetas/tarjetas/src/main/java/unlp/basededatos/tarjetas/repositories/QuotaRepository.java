package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.model.Quota;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Repository
public class QuotaRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long saveQuota(Quota quota) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(quota);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public Quota findQuotaById(Long id) throws TarjetasException {
        try {
            return (Quota) this.sessionFactory.getCurrentSession().createQuery("from Quota where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updateQuota(Quota quota) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(quota);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

}
