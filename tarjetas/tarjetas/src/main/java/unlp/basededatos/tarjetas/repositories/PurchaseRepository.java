package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT * FROM cash_payment", nativeQuery = true)
    String getStoreWithMoreSalesCash(@Param("month") String month);

    @Query(value = "SELECT * FROM monthly_payments", nativeQuery = true)
    Long getStoreWithMoreSalesMonthly(@Param("month") String month);
}
