package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unlp.basededatos.tarjetas.model.Quota;

public interface QuotaRepository extends JpaRepository<Quota, Long> {
}
