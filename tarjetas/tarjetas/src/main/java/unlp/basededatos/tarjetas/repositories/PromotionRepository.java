package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Promotion;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	
    @Query(nativeQuery = true, value ="SELECT * FROM promotion WHERE cuit_store = :cuit AND validity_start_date BETWEEN :date AND :date1 OR validity_end_date BETWEEN :date AND :date1")
    List<Promotion> findPromotionByCuitByDate(@Param("cuit") String cuit, @Param("date") Date date, @Param("date1") Date date1);

    @Query(nativeQuery = true, value ="SELECT p FROM Promotion p WHERE promotion.code = :code LIMIT 1")
    Promotion findPromotionByCode(@Param("code") String code);

    @Query(value = "SELECT promotionid"
            + "FROM cash_payment "
            + "GROUP BY promotionid"
            + "ORDER BY COUNT(*) DESC"
            + "LIMIT 1"
            + "INNER JOIN promotion", nativeQuery = true)
    Promotion getMostUsed();

}
