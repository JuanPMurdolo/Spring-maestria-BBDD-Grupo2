package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unlp.basededatos.tarjetas.model.Card;
import java.util.Date;
import java.util.List;

@Repository
public interface CardRepository  extends JpaRepository<Card, Long>{

    @Query("FROM Card as c WHERE c.expirationDate >= :date or c.expirationDate <= :date1")
    List<Card> findCardExpirationDate(@Param("date") Date date, @Param("date1") Date date1);
    
}
