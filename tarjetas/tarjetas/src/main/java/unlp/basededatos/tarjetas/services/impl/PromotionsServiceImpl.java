package unlp.basededatos.tarjetas.services.impl;

import unlp.basededatos.tarjetas.utils.TarjetasException;
import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.repositories.PromotionRepository;
import unlp.basededatos.tarjetas.repositories.impl.PromotionRepositoryImpl;
import unlp.basededatos.tarjetas.services.PromotionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionsServiceImpl implements PromotionsService{

    @Autowired
    private PromotionRepository repository;

    @Autowired
    private PromotionRepositoryImpl repository2;
    
    @Override
    @Transactional
    public Promotion createPromotion(Promotion promotion) throws TarjetasException {
        return this.repository.save(promotion);
    }

    @Override
    @Transactional
    public Promotion getPromotion(String id) throws TarjetasException {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Promotion updatePromotion(Promotion promotion, String id) throws TarjetasException  {
        Promotion promotion1 =  this.repository.findById(id).orElse(null);
        promotion1.setCode(promotion.getCode());
        promotion1.setComments(promotion.getComments());
        this.repository.save(promotion1);
        return promotion1;
    }

	@Override
    @Transactional
	public void deletePromotion(String code) throws TarjetasException {
		this.repository2.deletePromotion(code);
	}

}
