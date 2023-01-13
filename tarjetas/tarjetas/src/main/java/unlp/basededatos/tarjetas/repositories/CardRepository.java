package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.utils.BankException;
import unlp.basededatos.tarjetas.utils.CardException;

@Repository
public class CardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long saveCard(Card bank) throws CardException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(bank);
        } catch (Exception e) {
            throw new CardException(e.getMessage());
        }
    }

    public Card findCardById(Long id) throws CardException {
        try {
            return (Card) this.sessionFactory.getCurrentSession().createQuery("from Card where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new CardException(e.getMessage());
        }
    }

    public void updateCard(Card card) throws CardException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(card);
        } catch (Exception e) {
            throw new CardException(e.getMessage());
        }
    }

}
