package unlp.basededatos.tarjetas.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

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
    
	
    public Promotion getPromotionMostUsed() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("promotions"),
            Aggregation.group("promotions.code").count().as("count"),
            Aggregation.sort(Sort.Direction.DESC, "count"),
            Aggregation.limit(1)
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Purchase.class, Document.class);
        Document mostUsedPromotion = results.getUniqueMappedResult();

        String mostUsedPromotionCode = mostUsedPromotion.getString("_id");

        // buscamos la promoción por su código
        Promotion promotion = getPromotionByCode(mostUsedPromotionCode);

        return promotion;
    }

    private Promotion getPromotionByCode(String promotionCode) {
        Query query = new Query(Criteria.where("code").is(promotionCode));
        return mongoTemplate.findOne(query, Promotion.class);
    }
    
    

}
