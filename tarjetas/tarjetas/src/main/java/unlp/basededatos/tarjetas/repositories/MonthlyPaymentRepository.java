package unlp.basededatos.tarjetas.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlp.basededatos.tarjetas.model.CardHolder;
import unlp.basededatos.tarjetas.model.MonthlyPayments;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayments, Long>{

}
