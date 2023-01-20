package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unlp.basededatos.tarjetas.model.Bank;
import unlp.basededatos.tarjetas.model.Discount;
import unlp.basededatos.tarjetas.model.Payment;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ITarjetasServiceImpl implements ITarjetasService{
    
	@Autowired
    private PaymentRepository paymentRepository;

	@Autowired
	BanksService bankService;
	
    @Override
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException {
        //obtener todas los payments que se correspondan con el code
        List<Payment> lista = this.paymentRepository.findPaymentsByCode(code);
        for (int i = 0; i < lista.size(); i++) {
            //Leer el payment en dicha posicion
            Payment payment1 = lista.get(i);
            //se modifica la fecha de expiracion
            payment1.setFirstExpiration(first);
            payment1.setSecondExpiration(second);
            //Se actualiza el payment
            this.paymentRepository.updatePayment(payment1);
        }
        return lista;
    }
    
    public Discount addDiscountbyBank(Discount discount, Bank bank) throws TarjetasException {
    	
    	Discount discount1 = new Discount();
		discount1.setComments("Descuento en la Anonima");
		discount1.setCode("212");
		discount1.setCuitStore("123456");
		discount1.setDiscountPercentage(10);
		//no es necesario persistir porq ya persiste cuando crea el BANCO
		//promotionsService.createPromotion(discount1);
		
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        promotionsList.add(discount1);
        
		Bank bank1 = new Bank();
		bank1.setName("Banco Frances");
		bank1.setAddress("San Martin 1234");
		bank1.setCuit("20-1245454-2");
		bank1.setTelephone("98765412");
		bank1.setPromotions(promotionsList);
		bankService.createBank(bank1);
		
		return discount1;
		
	}

}
