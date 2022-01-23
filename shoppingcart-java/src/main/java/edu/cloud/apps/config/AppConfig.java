package edu.cloud.apps.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.cloud.apps.CartUseCaseImpl;
import edu.cloud.apps.ProductUseCaseImpl;
import edu.cloud.apps.adapters.db.CartRepositoryAdapter;
import edu.cloud.apps.adapters.db.ProductRepositoryAdapter;
import edu.cloud.apps.ports.web.CartUseCase;
import edu.cloud.apps.ports.web.ProductUseCase;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public ProductUseCase productUseCase(ProductRepositoryAdapter productRepository, ModelMapper modelMapper) {
		return new ProductUseCaseImpl(productRepository, modelMapper);
	}
	
	@Bean
	public CartUseCase cartUseCase(CartRepositoryAdapter cartRepository, ProductRepositoryAdapter productRepository, ModelMapper modelMapper) {
		return new CartUseCaseImpl(cartRepository, productRepository, modelMapper);
	}
	
}
