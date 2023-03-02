package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import unlp.basededatos.tarjetas.model.MonthlyPayments;

public interface MonthlyPaymentRepository extends MongoRepository<MonthlyPayments, Long> {

}
