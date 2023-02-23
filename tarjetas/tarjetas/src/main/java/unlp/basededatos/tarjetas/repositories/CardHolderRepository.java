package unlp.basededatos.tarjetas.repositories;

import unlp.basededatos.tarjetas.model.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long>{

    @Query(nativeQuery = true, value ="SELECT  COUNT(card_holder.id_cardholder) as cantidad, card.cardholder, card_holder.id_cardholder, "
    		+ "	card_holder.address, card_holder.telephone, card_holder.complete_name, card_holder.cuil, card_holder.entry, card_holder.dni "
    		+ " FROM  card "
    		+ "	INNER JOIN  card_holder "
    		+ "	ON  card.id_owner = card_holder.id_cardholder "
    		+ "	INNER JOIN  cash_payment "
    		+ "	ON card.id_card = cash_payment.id_card "
    		+ " group by 	card.cardholder, card_holder.id_cardholder  "
    		+ " order by  cantidad DESC "
    		+ " LIMIT 10")
	List<CardHolder> get10CardHolersWithMorePurchases();

}
