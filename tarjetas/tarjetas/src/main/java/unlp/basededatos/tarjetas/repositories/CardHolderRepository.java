package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.CardHolder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long>{

    @Query(nativeQuery = true, value ="SELECT 	* FROM 	card_holder LIMIT 10")
	public List<CardHolder> get10CardHolersWithMorePurchases();

}
