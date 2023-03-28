package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.client.result.UpdateResult;

import unlp.basededatos.tarjetas.model.Promotion;
import unlp.basededatos.tarjetas.model.Purchase;
import unlp.basededatos.tarjetas.repositories.PaymentRepository;
import unlp.basededatos.tarjetas.repositories.PurchaseRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPromotionRepository;
import unlp.basededatos.tarjetas.repositories.interfaces.IPurchaseRepository;
import unlp.basededatos.tarjetas.utils.PurchaseDTO;
import unlp.basededatos.tarjetas.utils.TarjetasException;

public class PromotionRepositoryImpl implements IPromotionRepository{
	
    private Pageable paging = PageRequest.of(0, 1);

    private final MongoTemplate mongoTemplate;

    public PromotionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public Long deletePromotion(String code) {
        Query query = new Query().addCriteria(Criteria.where("code").is(code));
        Update updateDefinition = new Update().set("borrado", true);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, Promotion.class);
        return updateResult.getModifiedCount();
    }

}
