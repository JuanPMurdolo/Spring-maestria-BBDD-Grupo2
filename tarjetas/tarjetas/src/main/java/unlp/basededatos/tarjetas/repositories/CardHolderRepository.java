package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.CardHolder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardHolderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long saveCardHolder(CardHolder cardHolder) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(cardHolder);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public CardHolder findCardHolderById(Long id) throws TarjetasException {
        try {
            return (CardHolder) this.sessionFactory.getCurrentSession().createQuery("from CardHolder where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updateCardHolder(CardHolder cardHolder) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(cardHolder);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

}
