package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import unlp.basededatos.tarjetas.model.Card;
import java.util.Date;
import java.util.List;

public interface CardRepository  extends MongoRepository<Card, String> {

    @Query("FROM Card as c WHERE c.expirationDate >= :date or c.expirationDate <= :date1")
    List<Card> findCardExpirationDate(@Param("date") Date date, @Param("date1") Date date1);
    
    
    
}
