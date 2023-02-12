package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unlp.basededatos.tarjetas.model.Quota;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
}
