package unlp.basededatos.tarjetas.services;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.MonthlyPayments;

import java.util.Optional;

public interface MonthlyPaymentsService {

	    MonthlyPayments createMonthlyPayments(MonthlyPayments monthlypayment) throws TarjetasException;

	    Optional<MonthlyPayments> getMonthlyPayments(String id) throws TarjetasException;

	    MonthlyPayments updateMonthlyPayments(MonthlyPayments monthlypayment,String id) throws TarjetasException;

		public boolean exists(String id);


}
