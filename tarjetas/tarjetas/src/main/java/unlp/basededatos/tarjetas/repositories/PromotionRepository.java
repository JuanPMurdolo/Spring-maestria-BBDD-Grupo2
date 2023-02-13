package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Promotion;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @Query(nativeQuery = true, value ="SELECT p FROM Promotion p WHERE promotion.cuit = :cuit AND p.validityStartDate BETWEEN :date AND :date1 OR p.validityEndDate BETWEEN :date AND :date1")
    public List<Promotion> findPromotionByCuitByDate(@Param("cuit") String cuit, @Param("date") Date date, @Param("date1") Date date1);

}
