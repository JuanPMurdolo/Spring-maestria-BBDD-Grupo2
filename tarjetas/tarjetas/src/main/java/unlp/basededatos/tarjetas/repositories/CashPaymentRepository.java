package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import unlp.basededatos.tarjetas.model.CashPayment;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;

public interface CashPaymentRepository extends MongoRepository<CashPayment, String> {

    @Query(value = "SELECT id "
    		+ "FROM CashPayment c   "
    		+ "GROUP BY id "
    		+ "ORDER BY COUNT(*) DESC  ")
    List<Long> getMostUsed(Pageable pageable);
    
    @Query(value = "SELECT c "
    		+ "FROM CashPayment c   "
    		+ "GROUP BY id "
    		+ "ORDER BY COUNT(*)  ")    
    List<String> getOccurences(Pageable pageable);

    @Query(value = "SELECT id "
    		+ "FROM MonthlyPayments m   "
    		+ "GROUP BY id "
    		+ "ORDER BY COUNT(*) DESC  ")
    List<Long> getMostUsedMonthly(Pageable pageable);

    @Query(value = "SELECT count(id) "
    		+ "FROM MonthlyPayments m   "
    		+ "GROUP BY id "
    		+ "ORDER BY COUNT(*)  ")
    List<String> getOccurencesMonthly(Pageable pageable);

	/*
	 * @Query(value = "db.collection.find()") String getPurchaseInfo(@Param("id")
	 * String id);
	 */
}
