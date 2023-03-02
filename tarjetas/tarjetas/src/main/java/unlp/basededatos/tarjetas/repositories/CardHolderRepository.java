package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.utils.BankDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface CardHolderRepository extends MongoRepository<CardHolder, Long> {

	/*
	 * @Query(nativeQuery = true, value
	 * ="SELECT  COUNT(card_holder.id_cardholder) as cantidad, card.cardholder, card_holder.id_cardholder, "
	 * +
	 * "	card_holder.address, card_holder.telephone, card_holder.complete_name, card_holder.cuil, card_holder.entry, card_holder.dni "
	 * + " FROM  card " + "	INNER JOIN  card_holder " +
	 * "	ON  card.id_owner = card_holder.id_cardholder " +
	 * "	INNER JOIN  cash_payment " + "	ON card.id_card = cash_payment.id_card "
	 * + " group by 	card.cardholder, card_holder.id_cardholder  " +
	 * " order by  cantidad DESC " + " LIMIT 10") List<CardHolder>
	 * get10CardHolersWithMorePurchases();
	 */
    
	//la SQL de arriba pasada a HQL
	@Query(value = "SELECT o "
			+ "FROM Card  c, CashPayment s "
			+ "INNER JOIN c.owner o "
			+ "INNER JOIN s.card "
			+ "GROUP by c.owner, o.id  "
			+ "ORDER by count(o.id) DESC  ")
    List<CardHolder> get10CardHolersWithMorePurchases(Pageable pageable);
	

}
