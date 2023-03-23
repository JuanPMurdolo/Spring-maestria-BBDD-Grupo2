package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import unlp.basededatos.tarjetas.model.Promotion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends MongoRepository<Promotion, String> {

    @Query(value = "SELECT p FROM Promotion p WHERE p.cuitStore = :cuit AND validityStartDate BETWEEN :date AND :date1 OR validityEndDate BETWEEN :date AND :date1")
    List<Promotion> findPromotionByCuitByDate(@Param("cuit") String cuit, @Param("date") Date date, @Param("date1") Date date1);

    @Query(value ="SELECT p FROM Promotion p WHERE p.code = :code")
    List<Promotion> findPromotionByCode(@Param("code") String code, Pageable pageable);

    @Query("UPDATE Promotion p "
    	+ " SET p.borrado = true "
    	+ " WHERE p.code = :code ")
	void deletePromotion(@Param("code") String code);


}
