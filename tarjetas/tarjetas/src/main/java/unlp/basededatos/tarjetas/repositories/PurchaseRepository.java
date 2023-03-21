package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

    @Query(value = "SELECT c.cuitStore as ciut, c.amount  as amount, c.store as store "
    		+ " FROM Payment p "
    		+ " INNER JOIN p.cashpayment c   "
    		+ " WHERE p.month   = :month    "
    		+ " GROUP BY c.cuitStore, c.amount ,c.store "
    		+ " ORDER BY c.amount DESC   ")
    List<PurchaseDTO> findStoreWithMoreSalesCash(@Param("month") String month, Pageable pageable);

    @Aggregation(pipeline = {
            "{'$lookup' : {'from' : 'order','localField' :'_id' ,'foreignField' : 'supplier.$id', 'as' : 'order'}}",
            "{'$unwind' : { path: '$order' } }",
            "{'$addFields' : { score: '$order.qualification.score' } }",
            "{'$match':{'month':{'$lte': ?0}}}"    })
    List<ArrayList>  findByScoreLessThanEqual(@Param("score") String month);	
    
    @Query(value = "SELECT m.cuitStore as ciut, m.amount as amount, m.store  as store "
    		+ "FROM Payment p, MonthlyPayments m "
    		+ "INNER JOIN p.quota "
    		+ " WHERE p.month   = :month   "
    		+ "GROUP BY m.cuitStore, m.amount ,m.store "
    		+ "ORDER BY m.amount DESC  ")
    List<PurchaseDTO> findStoreWithMoreSalesMonthly(@Param("month") String month, Pageable pageable);

	/*
	 * @Query(value = "db.collection.find()") String getPurchaseInfo(@Param("id")
	 * String id);
	 */
}
