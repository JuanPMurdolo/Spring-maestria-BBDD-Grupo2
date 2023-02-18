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

    @Query(value = "SELECT id_promotion " +
            "FROM cash_payment " +
            "GROUP BY id_promotion " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 1", nativeQuery = true)
    Long getMostUsed();

}
