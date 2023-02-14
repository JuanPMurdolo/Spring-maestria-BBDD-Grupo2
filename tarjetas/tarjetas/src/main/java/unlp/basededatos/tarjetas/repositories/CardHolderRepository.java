package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.model.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long>{

    @Query(nativeQuery = true, value ="SELECT * FROM 	card_holder LIMIT 10")
	List<CardHolder> get10CardHolersWithMorePurchases();

}
