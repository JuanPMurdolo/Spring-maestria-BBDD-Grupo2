package unlp.basededatos.tarjetas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import unlp.basededatos.tarjetas.model.*;
import unlp.basededatos.tarjetas.repositories.*;
import unlp.basededatos.tarjetas.utils.TarjetasException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ITarjetasServiceImpl implements ITarjetasService{
    
	@Autowired
    private PaymentRepository paymentRepository;

	@Autowired
	private BanksService bankService;
	
    @Autowired
    private BankRepository repository;

	@Autowired
	private CardRepository cardRepository;

	 @Autowired
	 private PurchaseRepository purchaseRepository;

	 @Autowired
	 private QuotaRepository quotaRepository;
    
    @Override
	@Transactional
    public List<Payment> updatePaymentsExpiration(String code, Date first, Date second) throws TarjetasException {
        //obtener todas los payments que se correspondan con el code
        List<Payment> lista = this.paymentRepository.findPaymentsByCode(code);
		List<Payment> lista2 = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            //Leer el payment en dicha posicion
            Payment payment1 = lista.get(i);
            //se modifica la fecha de expiracion
            payment1.setFirstExpiration(first);
            payment1.setSecondExpiration(second);
            //Se actualiza el payment
            this.paymentRepository.updatePayment(payment1);
			lista2.add(payment1);
        }
        return lista2;
    }
    
	@Transactional
	public Promotion addNewPromotion(Promotion promotion, Long id) throws TarjetasException {

		// Obtengo el Banco
		Bank bank = repository.findBankById(id);

		// Si el Banco no existe, retorno false
		if (bank ==  null) {
			System.out.println("El banco no existe");
			return null;
		}

		bank.addPromotion(promotion);
		bank = this.repository.findBankById(repository.saveBank(bank));

		return bank.getPromotions().get( bank.getPromotions().size() -1 );
	}

	@Override
	@Transactional
	public List<Card> getCardSoonExpiration() throws TarjetasException {
		return this.cardRepository.findCardExpirationDate();
	}

	@Override
	public Purchase getPurchaseInfo(Long id) throws TarjetasException {
		return this.purchaseRepository.findPurchaseById(id);
	}

	@Override
	public Promotion deletePromotion(String code) throws TarjetasException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public float totalQuota(Long id) throws TarjetasException {
		Float totalPayment = (float) 0;
		Payment payment = this.paymentRepository.findPaymentById(id);
		List<Quota> quotas = payment.getQuotas();
		for (int i = 0; i < quotas.size(); i++){
			System.out.println(quotas.get(i));
			totalPayment = totalPayment + quotas.get(i).getPrice();
		}
		return totalPayment;
	}

}
