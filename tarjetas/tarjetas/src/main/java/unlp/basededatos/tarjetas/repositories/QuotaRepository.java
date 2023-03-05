package unlp.basededatos.tarjetas.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import unlp.basededatos.tarjetas.model.Quota;

public interface QuotaRepository extends MongoRepository<Quota, String> {
}
