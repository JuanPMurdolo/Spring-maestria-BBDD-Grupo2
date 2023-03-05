package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import unlp.basededatos.tarjetas.model.Payment;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	@Query("from Payment where code = :code")
    List<Payment> findPaymentsByCode(@Param("code") String code);

    @Query(value = "db.collection.find()")
    float totalCashByMonth(@Param("month") String month);

    @Query(value = "db.collection.find()")
    float totalQuotasByMonth(@Param("month") String month);

}
