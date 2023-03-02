package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, Long> {

    @Query(value = "db.collection.find()")
    String getStoreWithMoreSalesCash(@Param("month") String month);

    @Query(value = "db.collection.find()")
    String getStoreWithMoreSalesMonthly(@Param("month") String month);

    @Query(value = "db.collection.find()")
    String getPurchaseInfo(@Param("id") Long id);
}
