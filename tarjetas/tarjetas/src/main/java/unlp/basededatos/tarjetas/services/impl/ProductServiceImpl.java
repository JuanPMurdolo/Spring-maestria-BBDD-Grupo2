package unlp.basededatos.tarjetas.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unlp.basededatos.tarjetas.model.Product;
import unlp.basededatos.tarjetas.repositories.ProductRepository;
import unlp.basededatos.tarjetas.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public double sumQuantities() {
		return productRepository.sumQuantities();
	}

	@Override
	public double sumQuantitiesWithCondition(boolean status) {
		return productRepository.sumQuantitiesWithCondition(status);
	}

	@Override
	public double total(boolean status) {
		return productRepository.total(status);
	}

	@Override
	public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
	}
	
	@Override
	public double min() {
		return productRepository.min();
	}

	@Override
	public double max() {
		return productRepository.max();
	}

	@Override
	public long count1() {
		return productRepository.count();
	}

	@Override
	public long count2(boolean status) {
		return productRepository.count2(status);
	}
		
}
