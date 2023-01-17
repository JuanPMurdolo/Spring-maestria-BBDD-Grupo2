package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Repository
public class CardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long saveCard(Card bank) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(bank);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public Card findCardById(Long id) throws TarjetasException {
        try {
            return (Card) this.sessionFactory.getCurrentSession().createQuery("from Card where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updateCard(Card card) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(card);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

}
