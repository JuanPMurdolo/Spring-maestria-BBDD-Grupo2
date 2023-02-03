package unlp.basededatos.tarjetas.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.utils.TarjetasException;

@Repository
public class PromotionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Long savePromotion(Promotion promotion) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            return (Long) session.save(promotion);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public Promotion findPromotionById(Long id) throws TarjetasException {
        try {
            return (Promotion) this.sessionFactory.getCurrentSession().createQuery("from Promotion where id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }
    
    public Promotion findPromotionByCode(String code) throws TarjetasException {
        try {
            return (Promotion) this.sessionFactory.getCurrentSession().createQuery("from Promotion where code = :code").setParameter("code", code).uniqueResult();
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

    public void updatePromotion(Promotion promotion) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(promotion);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }
    }

	public void deletePromotion(Promotion promotion) throws TarjetasException {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            promotion.setBorrado(true);
            session.save(promotion);
        } catch (Exception e) {
            throw new TarjetasException(e.getMessage());
        }		
	}
    
}