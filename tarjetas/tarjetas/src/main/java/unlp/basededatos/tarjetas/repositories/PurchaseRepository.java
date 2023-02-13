package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unlp.basededatos.tarjetas.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
