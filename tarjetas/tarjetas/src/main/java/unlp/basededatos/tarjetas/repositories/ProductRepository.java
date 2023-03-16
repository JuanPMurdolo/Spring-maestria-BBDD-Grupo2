package unlp.basededatos.tarjetas.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import unlp.basededatos.tarjetas.model.Product;
import java.util.Date;
import java.util.List;

public interface ProductRepository  extends MongoRepository<Product, String> {

	@Aggregation(pipeline = {
			"{$group: { _id: '', total: {$sum: $quantity}}}"
		})
	public double sumQuantities();

	// $match must be placed in front of $group
	@Aggregation(pipeline = {
		"{$match: { status: ?0}}",
		"{$group: { _id: '', total: {$sum: $quantity}}}"
	})
	public double sumQuantitiesWithCondition(boolean status);

	// $match must be placed in front of $group
	@Aggregation(pipeline = {
		"{$match: { status: ?0}}",
		"{$group: { _id: '', total: { $sum: { $multiply: [ $price, $quantity ]}}}}"
	})
	public double total(boolean status);
	
	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $price }}}" })
	public double max();

	@Aggregation(pipeline = { "{$group: { _id: '', total: {$min: $price }}}" })
	public double min();

	@Query(value = "{status: ?0}", count = true)
	public long count2(boolean status);
    
}
