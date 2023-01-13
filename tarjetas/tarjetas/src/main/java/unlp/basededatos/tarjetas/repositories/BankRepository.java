package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.utils.BankException;
import unlp.basededatos.tarjetas.model.Bank;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long saveBank(Bank bank) throws BankException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(bank);
        } catch (Exception e) {
            throw new BankException(e.getMessage());
        }
    }

    public Bank findBankById(Long id) throws BankException {
        try {
            return (Bank) this.sessionFactory.getCurrentSession().createQuery("from Bank where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new BankException(e.getMessage());
        }
    }

    public void updateBank(Bank bank) throws BankException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(bank);
        } catch (Exception e) {
            throw new BankException(e.getMessage());
        }
    }

}
