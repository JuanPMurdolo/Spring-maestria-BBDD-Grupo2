package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Card;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

    public List<Card> findCardExpirationDate() throws TarjetasException {
        Date date = new Date();
        LocalDate localDate = LocalDate.now().plusDays(30);
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date1);
        System.out.println(date);
        try {
            return (List<Card>) this.sessionFactory.getCurrentSession().createQuery("from Card where expirationDate <= :date and expirationDate > :date1").setParameter("date", date).setParameter("date1", date1).getResultList();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

}
