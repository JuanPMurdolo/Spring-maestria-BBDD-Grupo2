package unlp.basededatos.tarjetas.services;

import java.util.Optional;

import unlp.basededatos.tarjetas.model.Product;

public interface ProductService {

	public double sumQuantities();

	public double sumQuantitiesWithCondition(boolean status);

	public double total(boolean status);

	public Optional<Product> getProduct(String id);
	
	public double min();

	public double max();
	
	public long count1();

	public long count2(boolean status);
}
